package com.jtemp.stage.net.netty;

import com.jtemp.stage.common.thread.NamedThreadFactory;
import com.jtemp.stage.net.NetServerAdapter;
import com.jtemp.stage.net.NetServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author ZMS
 * @Date 2018/10/13 11:53 AM
 */
public class NettyTcpServer extends NetServerAdapter {

    private NioEventLoopGroup bossEventLoopGroup;

    public NettyTcpServer(NetServerHandler handler, String name) {
        super(handler, name);
    }

    @Override
    protected void start0(int listenPort) throws Exception {
        final NettyTcpServer _this = this;
        bossEventLoopGroup = new NioEventLoopGroup(0, new NamedThreadFactory(name()));
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEventLoopGroup, NettyHelper.workerEventLoopGroup())
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.ALLOCATOR, NettyHelper.byteBufAllocator())
            .childOption(ChannelOption.ALLOCATOR, NettyHelper.byteBufAllocator())
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_REUSEADDR, true)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline()
                        .addLast("decoder", new NettyProtocolDecoder())
                        .addLast("encoder", new NettyProtocolEncoder())
                        .addLast("handler", new NettyServerHandler(handler, _this));
                }
            });

        int retry = 3;
        while (retry > 0) {
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress("127.0.0.1", 8080));
            channelFuture.await();
            if (channelFuture.isSuccess()) {
                return;
            } else {
                retry--;
                Thread.sleep(1000);
            }
        }
        stop();
    }

    @Override
    public int clientCount() {
        return 0;
    }

    @Override
    protected void stop0() {
        bossEventLoopGroup.shutdownGracefully();
    }
}
