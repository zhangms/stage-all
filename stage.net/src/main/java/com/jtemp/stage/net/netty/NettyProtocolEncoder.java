package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetException;
import com.jtemp.stage.net.protocol.NetProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author ZMS
 * @Date 2018/10/20 4:19 PM
 */
public class NettyProtocolEncoder extends MessageToByteEncoder<Object> {

    private static final int MARK_LENGTH = 0;

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        try {

            NetProtocol protocol = (NetProtocol)o;
            NettyByteBufferWrapper wrapper = NettyByteBufferWrapper.wrap(byteBuf);

            //长度标记
            int markIndex = wrapper.writerIndex();
            wrapper.writeInt(MARK_LENGTH);
            int beginIndex = wrapper.writerIndex();

            //数据体
            wrapper.writeInt(protocol.getCommandId());
            protocol.encode(wrapper);

            //回写长度
            int length = wrapper.writerIndex() - beginIndex;
            byteBuf.setInt(markIndex, length);
        } catch (Exception e) {
            channelHandlerContext.close();
            throw new NetException("protocol encode error", e);
        }
    }
}
