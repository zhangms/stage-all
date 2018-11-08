package com.jtemp.stage.net;

import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/10/13 10:35 AM
 */
public interface NetClient {

    /**
     * 链接服务器
     *
     * @param url
     * @throws NetException
     */
    void connect(NetUrl url) throws NetException;

    /**
     * 获取网络链接
     *
     * @return
     */
    NetConnection getConnection();

    /**
     * 关闭客户端
     */
    void close();

    /**
     * 发送数据包
     *
     * @param dataPackage
     */
    void send(NetProtocol dataPackage);
}
