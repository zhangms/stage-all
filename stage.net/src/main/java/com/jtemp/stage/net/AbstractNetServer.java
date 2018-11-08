package com.jtemp.stage.net;

import com.jtemp.stage.common.thread.NamedThreadFactory;
import com.jtemp.stage.net.protocol.NetConnection;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ZMS
 * @Date 2018/10/13 1:26 PM
 */
public abstract class AbstractNetServer implements NetServer, Runnable {

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
     * host
     */
    protected String bindHost;

    /**
     * 是否在运行
     */
    protected AtomicBoolean running = new AtomicBoolean();

    private ScheduledExecutorService scanner;

    /**
     * 链接集合
     */
    protected Set<NetConnection> connections = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public AbstractNetServer(NetServerHandler handler, String name) {
        this.handler = handler;
        this.name = name;
        scanner = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory(name, true));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public final void start(String bindHost, int listenPort) throws Exception {
        if (running.compareAndSet(false, true)) {
            this.bindHost = bindHost;
            this.listenPort = listenPort;
            start0();
            startConnectionScanner();
        } else {
            throw new NetException("server was started");
        }
    }

    private void startConnectionScanner() {
        scanner.scheduleAtFixedRate(this, 5, 5, TimeUnit.SECONDS);
    }

    @Override
    public final void stop() {
        if (running.compareAndSet(true, false)) {
            scanner.shutdownNow();
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

    @Override
    public String bindHost() {
        return bindHost;
    }

    protected InetSocketAddress getBindAddress() {
        if (bindHost == null) {
            return new InetSocketAddress(listenPort);
        }
        return new InetSocketAddress(bindHost, listenPort);
    }

    /**
     * start impl
     *
     * @throws Exception
     */
    protected abstract void start0() throws Exception;

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public void channelActive(NetConnection connection) {
        connections.add(connection);
        handler.channelActive(connection);
    }

    @Override
    public void channelInactive(NetConnection connection) {
        connections.remove(connection);
        handler.channelInactive(connection);
    }

    @Override
    public int clientCount() {
        return connections.size();
    }

    @Override
    public void run() {
        try {
            for (NetConnection connection : connections) {
                if (System.currentTimeMillis() - connection.getLastReceiveAt() > 60000) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
