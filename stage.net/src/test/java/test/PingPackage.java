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

    long t0 = System.currentTimeMillis();
    long t1 = System.currentTimeMillis();

    @Override
    public int getCommandId() {
        return COMMAND_ID;
    }

    @Override
    public void encode(ByteBufferWrapper wrapper) {
        wrapper.writeLong(t0);
        wrapper.writeLong(t1);
    }

    @Override
    public void decode(NettyByteBufferWrapper wrapper) {
        t0 = wrapper.readLong();
        t1 = wrapper.readLong();
    }

    public long getT0() {
        return t0;
    }

    public PingPackage setT0(long t0) {
        this.t0 = t0;
        return this;
    }

    public long getT1() {
        return t1;
    }

    public PingPackage setT1(long t1) {
        this.t1 = t1;
        return this;
    }

    @Override
    public String toString() {
        return "[ping]" + new Date(t0) + "|" + new Date(t1) + "|" + (t1 - t0) + "(ms)";
    }
}
