package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.TcpNettyServer;

/**
 * @author ZMS
 * @Date 2018/10/13 11:54 AM
 */
public final class ServerFactory {

    public static Server createTcpServerNetty(ServerHandler handler) {
        return new TcpNettyServer(handler, "TCP-NETTY");
    }

}
