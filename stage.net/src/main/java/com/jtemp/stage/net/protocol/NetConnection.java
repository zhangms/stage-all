package com.jtemp.stage.net.protocol;

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

}
