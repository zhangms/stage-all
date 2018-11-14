package com.jtemp.stage.net;

import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/10/13 11:55 AM
 */
public interface NetServerHandler extends NetHandler {

    /**
     * 读
     *
     * @param connection
     * @param dataPackage
     */
    void channelRead(NetConnection connection, NetProtocol dataPackage);

    void channelActive(NetConnection connection);

    void channelInactive(NetConnection connection);
}
