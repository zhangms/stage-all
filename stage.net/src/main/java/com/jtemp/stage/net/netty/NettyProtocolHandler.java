package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.ServerHandler;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ZMS
 * @Date 2018/10/20 4:22 PM
 */
public class NettyProtocolHandler extends SimpleChannelInboundHandler<NetProtocol> {

    ServerHandler serverHandler;

    public NettyProtocolHandler(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NetProtocol dataPackage) throws Exception {



    }
}
