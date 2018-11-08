package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.Client;
import com.jtemp.stage.net.ClientHandler;
import com.jtemp.stage.net.NetException;
import com.jtemp.stage.net.NetURL;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZMS
 * @Date 2018/11/6 7:34 PM
 */
public class NettyClient implements Client {

    private AtomicBoolean connected = new AtomicBoolean();

    private ClientHandler handler;

    private NetURL url;

    public NettyClient(ClientHandler handler) {
        this.handler = handler;
    }

    @Override
    public void connect(NetURL url) throws NetException {
        if (connected.compareAndSet(false, true)) {
            connect0(url);
        } else {
            throw new NetException("client was connected");
        }
    }

    private void connect0(NetURL url) {
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
                        .addLast("handler", new NettyClientHandler(handler));
                }
            });
    }

    @Override
    public void close() {
    }
}
