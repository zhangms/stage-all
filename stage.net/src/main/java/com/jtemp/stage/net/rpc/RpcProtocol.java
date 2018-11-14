package com.jtemp.stage.net.rpc;

import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/11/14 9:12 PM
 */
public abstract class RpcProtocol implements NetProtocol {

    abstract void setRequestId(String requestId);

    abstract String getRequestId();

}
