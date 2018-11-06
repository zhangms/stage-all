package com.jtemp.stage.net.protocol;

import com.jtemp.stage.net.netty.NettyByteBufferWrapper;

/**
 * @author ZMS
 * @Date 2018/10/20 4:27 PM
 */
public interface NetProtocol {

    /**
     * 指令ID
     *
     * @return
     */
    int getCommandId();

    /**
     * encode
     *
     * @param wrapper
     */
    void encode(ByteBufferWrapper wrapper);

    /**
     * decode
     *
     * @param wrapper
     */
    void decode(NettyByteBufferWrapper wrapper);
}
