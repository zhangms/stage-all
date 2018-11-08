package com.jtemp.stage.net;

import com.jtemp.stage.net.protocol.NetConnection;

/**
 * @author ZMS
 * @Date 2018/10/13 10:35 AM
 */
public interface NetServer {

    String name();

    int listenPort();

    String bindHost();

    int clientCount();

    void start(String bindHost, int listenPort) throws Exception;

    void stop();

    boolean isRunning();

    public abstract void channelActive(NetConnection connection);

    public abstract void channelInactive(NetConnection connection);

}
