package com.jtemp.stage.net;

import com.jtemp.stage.net.netty.NettyClient;

/**
 * @author ZMS
 * @Date 2018/11/6 7:27 PM
 */
public final class ClientFactory {

    public static Client createClient(ClientHandler handler) {
        return createClientNetty(handler);
    }

    private static Client createClientNetty(ClientHandler handler) {
        return new NettyClient(handler);
    }

}
