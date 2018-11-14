package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetServerHandler;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ZMS
 * @Date 2018/10/20 4:22 PM
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<NetProtocol> {

    private static final String KEY_CONNECTION = "netty:conn";

    NetServerHandler serverHandler;

    NettyTcpServer nettyTcpServer;

    public NettyServerHandler(NetServerHandler handler, NettyTcpServer nettyTcpServer) {
        this.serverHandler = handler;
        this.nettyTcpServer = nettyTcpServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NetProtocol dataPackage) throws Exception {
        NettyConnection connection = (NettyConnection)channelHandlerContext.pipeline().get(KEY_CONNECTION);
        connection.setLastReceiveAt(System.currentTimeMillis());
        serverHandler.channelRead(connection, dataPackage);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        NettyConnection connection = new NettyConnection(ctx.channel(), serverHandler);
        ctx.pipeline().addFirst(KEY_CONNECTION, connection);
        nettyTcpServer.channelActive(connection);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        NettyConnection connection = (NettyConnection)ctx.pipeline().remove(KEY_CONNECTION);
        nettyTcpServer.channelInactive(connection);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }
}
