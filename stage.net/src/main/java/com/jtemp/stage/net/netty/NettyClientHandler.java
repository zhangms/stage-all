package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetClientHandler;
import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ZMS
 * @Date 2018/11/7 9:17 PM
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<NetProtocol> {

    NetClientHandler handler;

    NettyClient client;

    public NettyClientHandler(NetClientHandler handler, NettyClient client) {
        this.handler = handler;
        this.client = client;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NetProtocol dataPackage) throws Exception {
        NettyConnection conn = (NettyConnection)client.getConnection();
        conn.setLastReceiveAt(System.currentTimeMillis());
        handler.channelRead(client, dataPackage);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        NetConnection connection = new NettyConnection(ctx.channel());
        client.setConnection(connection);
        handler.channelActive(client);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        handler.channelInactive(client);
    }
}
