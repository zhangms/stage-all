package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyClient;

/**
 * @author ZMS
 * @Date 2018/11/6 7:27 PM
 */
public final class NetClientFactory {

    public static NetClient createClient(NetClientHandler handler) {
        return createClientNetty(handler);
    }

    private static NetClient createClientNetty(NetClientHandler handler) {
        return new NettyClient(handler);
    }

}
