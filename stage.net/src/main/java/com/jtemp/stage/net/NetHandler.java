package com.jtemp.stage.net;

import com.jtemp.stage.net.protocol.NetProtocol;

import java.util.concurrent.Future;

/**
 * @author ZMS
 * @Date 2018/11/14 8:01 PM
 */
public interface NetHandler {

    Future<NetProtocol> wrapRequestFuture(NetProtocol dataPackage);
}
