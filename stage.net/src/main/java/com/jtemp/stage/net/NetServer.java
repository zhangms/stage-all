package com.jtemp.stage.net;

/**
 * @author ZMS
 * @Date 2018/10/13 10:35 AM
 */
public interface NetServer {

    String name();

    int listenPort();

    int clientCount();

    void start(int listenPort) throws Exception;

    void stop();

    boolean isRunning();

}
