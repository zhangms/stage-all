package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyTcpServer;

/**
 * @author ZMS
 * @Date 2018/10/13 11:54 AM
 */
public final class ServerFactory {

    public static Server createTcpServerNetty(ServerHandler handler) {
        return new NettyTcpServer(handler, "TCP-NETTY");
    }

}
