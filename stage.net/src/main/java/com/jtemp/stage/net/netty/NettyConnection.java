package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.protocol.NetConnection;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author ZMS
 * @Date 2018/11/6 3:29 PM
 */
public class NettyConnection extends ChannelHandlerAdapter implements NetConnection {

    ChannelHandlerContext channelHandlerContext;

    public NettyConnection(ChannelHandlerContext ctx) {
        this.channelHandlerContext = ctx;
    }

    @Override
    public void close() {
        this.channelHandlerContext.close();
    }
}
