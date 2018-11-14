package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetException;
import com.jtemp.stage.net.NetHandler;
import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author ZMS
 * @Date 2018/11/6 3:29 PM
 */
public class NettyConnection extends ChannelHandlerAdapter implements NetConnection {

    long lastReceiveAt = System.currentTimeMillis();

    long lastSendAt = System.currentTimeMillis();

    Channel channel;

    NetHandler netHandler;

    ChannelFutureListener writeListener = channelFuture -> {
        if (!channelFuture.isSuccess()) {
            if (!channel.isActive()) {
                channel.close();
            }
        }
    };

    public NettyConnection(Channel channel, NetHandler netHandler) {
        this.channel = channel;
        this.netHandler = netHandler;
    }

    public Channel getChannel() {
        return channel;
    }

    public NetHandler getNetHandler() {
        return netHandler;
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
    public Future<NetProtocol> futureInvoke(NetProtocol dataPackage) {
        if (channel != null) {
            lastSendAt = System.currentTimeMillis();
            channel.writeAndFlush(dataPackage).addListener(writeListener);
            return netHandler.wrapRequestFuture(dataPackage);
        }
        CompletableFuture<NetProtocol> ret = new CompletableFuture<>();
        ret.completeExceptionally(new NetException("netty channel is null"));
        return ret;
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
