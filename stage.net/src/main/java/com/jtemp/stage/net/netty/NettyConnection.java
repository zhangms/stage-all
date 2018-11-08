package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;

/**
 * @author ZMS
 * @Date 2018/11/6 3:29 PM
 */
public class NettyConnection extends ChannelHandlerAdapter implements NetConnection {

    long lastReceiveAt = System.currentTimeMillis();

    long lastSendAt = System.currentTimeMillis();

    Channel channel;

    ChannelFutureListener writeListener = channelFuture -> {
        if (!channelFuture.isSuccess()) {
            if (!channel.isActive()) {
                channel.close();
            }
        }
    };

    public Channel getChannel() {
        return channel;
    }

    public NettyConnection(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void close() {
        channel.close();
    }

    @Override
    public void send(NetProtocol dataPackage) {
        if (channel != null) {
            lastSendAt = System.currentTimeMillis();
            channel.writeAndFlush(dataPackage).addListener(writeListener);
        }
    }

    @Override
    public boolean isActive() {
        return channel != null && channel.isActive();
    }

    @Override
    public long getLastReceiveAt() {
        return lastReceiveAt;
    }

    public void setLastReceiveAt(long timeMillis) {
        this.lastReceiveAt = timeMillis;
    }

    @Override
    public String toString() {
        return channel == null ? super.toString() : channel.toString();
    }
}
