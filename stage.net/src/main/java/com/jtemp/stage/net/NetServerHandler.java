package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyConnection;
import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/10/13 11:55 AM
 */
public interface NetServerHandler {

    /**
     * 读
     *
     * @param connection
     * @param dataPackage
     */
    void channelRead(NettyConnection connection, NetProtocol dataPackage);

    void channelActive(NettyConnection connection);

    void channelInactive(NettyConnection connection);
}
