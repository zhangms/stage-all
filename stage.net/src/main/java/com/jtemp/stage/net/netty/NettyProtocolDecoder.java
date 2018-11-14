package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetException;
import com.jtemp.stage.net.protocol.NetProtocol;
import com.jtemp.stage.net.protocol.NetProtocolManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author ZMS
 * @Date 2018/10/20 4:19 PM
 */
public class NettyProtocolDecoder extends ByteToMessageDecoder {

    private static final int LENGTH_BYTES = 4;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
        throws Exception {
        try {
            if (byteBuf.readableBytes() < LENGTH_BYTES) {
                return;
            }
            byteBuf.markReaderIndex();
            int length = byteBuf.readInt();
            if (byteBuf.readableBytes() < length) {
                byteBuf.resetReaderIndex();
                return;
            }
            int commandId = byteBuf.readInt();
            NetProtocol protocol = NetProtocolManager.createProtocol(commandId);
            if (protocol == null) {
                channelHandlerContext.close();
                throw new NetException("protocol not exists: " + Integer.toHexString(commandId));
            }
            protocol.decode(NettyByteBufferWrapper.wrap(byteBuf));
            list.add(protocol);
        } catch (Exception e) {
            throw new NetException("decode error", e);
        }
    }
}
