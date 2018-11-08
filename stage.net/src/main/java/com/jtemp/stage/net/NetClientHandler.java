package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyClient;
import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/11/7 9:02 PM
 */
public interface NetClientHandler {

    void channelActive(NettyClient client);

    void channelInactive(NettyClient client);

    void channelRead(NettyClient client, NetProtocol dataPackage);
}
