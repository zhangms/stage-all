package com.jtemp.stage.net.netty;

import com.jtemp.stage.common.thread.NamedThreadFactory;
import com.jtemp.stage.net.AbstractServer;
import com.jtemp.stage.net.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author ZMS
 * @Date 2018/10/13 11:53 AM
 */
public class TcpNettyServer extends AbstractServer {

    private NioEventLoopGroup bossEventLoopGroup;

    public TcpNettyServer(ServerHandler handler, String name) {
        super(handler, name);
    }

    @Override
    protected void start0(int listenPort) {
        bossEventLoopGroup = new NioEventLoopGroup(0, new NamedThreadFactory(name()));
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEventLoopGroup, NettyHelper.workerEventLoopGroup())
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.ALLOCATOR, NettyHelper.byteBufAllocator())
            .childOption(ChannelOption.ALLOCATOR, NettyHelper.byteBufAllocator())
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline();
                }
            });
    }

    @Override
    public int clientCount() {
        return 0;
    }

    @Override
    public void stop() {

    }
}
