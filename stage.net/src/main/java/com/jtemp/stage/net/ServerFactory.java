package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyTcpServer;

/**
 * @author ZMS
 * @Date 2018/10/13 11:54 AM
 */
public final class ServerFactory {

    /**
     * 创建服务器
     *
     * @param handler
     * @return
     */
    public Server createTcpServer(ServerHandler handler) {
        return createTcpServerNetty(handler);
    }

    public Server createTcpServerNetty(ServerHandler handler) {
        return new NettyTcpServer(handler, "TCP-NETTY");
    }
}
