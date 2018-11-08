package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.ClientHandler;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ZMS
 * @Date 2018/11/7 9:17 PM
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<NetProtocol> {

    public NettyClientHandler(ClientHandler handler) {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NetProtocol netProtocol) throws Exception {

    }
}
