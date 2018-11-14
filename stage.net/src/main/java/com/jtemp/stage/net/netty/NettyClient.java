package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.*;
import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZMS
 * @Date 2018/11/6 7:34 PM
 */
public class NettyClient implements NetClient {

    private AtomicBoolean connected = new AtomicBoolean();

    private NetClientHandler handler;

    private NetUrl url;

    private volatile NetConnection connection;

    public NettyClient(NetClientHandler handler) {
        this.handler = handler;
    }

    @Override
    public void connect(NetUrl url) throws NetException {
        if (connected.compareAndSet(false, true)) {
            connect0(url);
        } else {
            throw new NetException("client was connected");
        }
    }

    private void connect0(NetUrl url) throws NetException {
        NettyClient client = this;
        this.url = url;
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(NettyHelper.workerEventLoopGroup())
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.SO_REUSEADDR, true)
            .option(ChannelOption.ALLOCATOR, NettyHelper.byteBufAllocator())
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast("decoder", new NettyProtocolDecoder())
                        .addLast("encoder", new NettyProtocolEncoder())
                        .addLast("handler", new NettyClientHandler(handler, client));
                }
            });

        int connectTimeout = url.getParameter(NetConstants.KEY_CONNECT_TIMEOUT, NetConstants.DEF_CONNECTION_TIMEOUT);
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout);

        ChannelFuture future = bootstrap.connect(url.getHost(), url.getPort());

        boolean sync = url.getParameter(NetConstants.KEY_CONNECT_SYNC, NetConstants.DEF_CONNECTION_SYNC);
        if (sync) {
            if (future.awaitUninterruptibly(connectTimeout) && future.isSuccess() && future.channel().isActive()) {
                int retryTime = 0;
                while (retryTime < connectTimeout && connection == null) {
                    retryTime = +100;
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                    }
                }
            } else {
                future.cancel(true);
                future.channel().close();
                connected.set(false);
                throw new NetException("netty client connect failed:" + url.toString());
            }
        } else {
            future.addListener((ChannelFutureListener)future1 -> {
                if (future1.isSuccess() && future1.channel().isActive()) {
                    return;
                }
                client.close();
                throw new NetException("netty client connect failed:" + url.toString());
            });
        }
    }

    protected void setConnection(NetConnection connection) {
        this.connection = connection;
    }

    @Override
    public NetConnection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        connected.set(false);
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public void send(NetProtocol dataPackage) {
        if (connected.get() && connection != null) {
            connection.send(dataPackage);
        }
    }

    @Override
    public Future<NetProtocol> futureInvoke(NetProtocol dataPackage) {
        if (connected.get() && connection != null) {
            return connection.futureInvoke(dataPackage);
        }
        CompletableFuture<NetProtocol> ret = new CompletableFuture<>();
        if (!connected.get()) {
            ret.completeExceptionally(new NetException("not connected"));
        } else if (connection == null) {
            ret.completeExceptionally(new NetException("connection is null"));
        }
        return ret;
    }
}
