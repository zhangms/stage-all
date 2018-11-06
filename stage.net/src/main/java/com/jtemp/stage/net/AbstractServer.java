package com.jtemp.stage.net;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZMS
 * @Date 2018/10/13 1:26 PM
 */
public abstract class AbstractServer implements Server {

    /**
     * 处理器
     */
    protected ServerHandler handler;

    /**
     * 服务名称
     */
    private String name;

    /**
     * 端口
     */
    protected int listenPort;

    /**
     * 是否在运行
     */
    protected AtomicBoolean running = new AtomicBoolean();

    public AbstractServer(ServerHandler handler, String name) {
        this.handler = handler;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void start(int listenPort) {
        if (!running.compareAndSet(false, true)) {
            return;
        }
        start0(listenPort);
    }

    @Override
    public int listenPort() {
        return listenPort;
    }

    /**
     * start impl
     *
     * @param listenPort
     */
    protected abstract void start0(int listenPort);

    @Override
    public boolean isRunning() {
        return running.get();
    }
}
