package com.jtemp.stage.net.protocol;

import java.util.concurrent.Future;

/**
 * @author ZMS
 * @Date 2018/10/13 10:35 AM
 */
public interface NetConnection {

    /**
     * 关闭链接
     */
    void close();

    /**
     * 发送数据包
     *
     * @param dataPackage
     */
    void send(NetProtocol dataPackage);

    boolean isActive();

    /**
     * 上一次读包时间
     *
     * @return
     */
    long getLastReceiveAt();

    /**
     * 调用
     *
     * @param dataPackage
     * @return
     */
    Future<NetProtocol> futureInvoke(NetProtocol dataPackage);
}
