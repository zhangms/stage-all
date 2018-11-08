package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyTcpServer;

/**
 * @author ZMS
 * @Date 2018/10/13 11:54 AM
 */
public final class NetServerFactory {

    /**
     * 创建服务器
     *
     * @param handler
     * @return
     */
    public static NetServer createTcpServer(NetServerHandler handler) {
        return createTcpServerNetty(handler);
    }

    public static NetServer createTcpServerNetty(NetServerHandler handler) {
        return new NettyTcpServer(handler, "TCP-NETTY");
    }
}
