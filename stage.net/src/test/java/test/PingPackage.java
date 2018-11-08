package test;

import com.jtemp.stage.net.netty.NettyByteBufferWrapper;
import com.jtemp.stage.net.protocol.ByteBufferWrapper;
import com.jtemp.stage.net.protocol.NetProtocol;

import java.util.Date;

/**
 * @author ZMS
 * @Date 2018/11/8 2:18 PM
 */
public class PingPackage implements NetProtocol {

    public static final int COMMAND_ID = 0x0001;

    long time = System.currentTimeMillis();

    @Override
    public int getCommandId() {
        return COMMAND_ID;
    }

    @Override
    public void encode(ByteBufferWrapper wrapper) {
        wrapper.writeLong(time);
    }

    @Override
    public void decode(NettyByteBufferWrapper wrapper) {
        time = wrapper.readLong();
    }

    @Override
    public String toString() {
        return "[ping]" + new Date(time);
    }
}
