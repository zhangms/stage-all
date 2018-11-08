package com.jtemp.stage.net;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZMS
 * @Date 2018/10/13 1:26 PM
 */
public abstract class NetServerAdapter implements NetServer {

    /**
     * 处理器
     */
    protected NetServerHandler handler;

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

    public NetServerAdapter(NetServerHandler handler, String name) {
        this.handler = handler;
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public final void start(int listenPort) throws Exception {
        if (running.compareAndSet(false, true)) {
            start0(listenPort);
        }
    }

    @Override
    public final void stop() {
        if (running.compareAndSet(true, false)) {
            stop0();
        }
    }

    /**
     * stop impl
     */
    protected abstract void stop0();

    @Override
    public int listenPort() {
        return listenPort;
    }

    /**
     * start impl
     *
     * @param listenPort
     */
    protected abstract void start0(int listenPort) throws Exception;

    @Override
    public boolean isRunning() {
        return running.get();
    }
}
