package com.jtemp.stage.net.netty;

import com.jtemp.stage.net.NetConstants;
import com.jtemp.stage.net.protocol.ByteBufferWrapper;
import io.netty.buffer.ByteBuf;

/**
 * @author ZMS
 * @Date 2018/11/6 2:12 PM
 */
public class NettyByteBufferWrapper implements ByteBufferWrapper {

    public static NettyByteBufferWrapper wrap(ByteBuf byteBuf) {
        return new NettyByteBufferWrapper(byteBuf);
    }

    private ByteBuf byteBuf;

    private NettyByteBufferWrapper(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    @Override
    public void writeByte(byte data) {
        byteBuf.writeByte(data);
    }

    @Override
    public byte readByte() {
        return byteBuf.readByte();
    }

    @Override
    public void writeInt(int data) {
        byteBuf.writeInt(data);
    }

    @Override
    public void writeBytes(byte[] data) {
        byteBuf.writeBytes(data);
    }

    @Override
    public int readableBytes() {
        return byteBuf.readableBytes();
    }

    @Override
    public int readInt() {
        return byteBuf.readInt();
    }

    @Override
    public void readBytes(byte[] data) {
        byteBuf.readBytes(data);
    }

    @Override
    public void writeLong(long data) {
        byteBuf.writeLong(data);
    }

    @Override
    public long readLong() {
        return byteBuf.readLong();
    }

    @Override
    public void writeFloat(float data) {
        byteBuf.writeFloat(data);
    }

    @Override
    public float readFloat() {
        return byteBuf.readFloat();
    }

    @Override
    public void writeDouble(double data) {
        byteBuf.writeDouble(data);
    }

    @Override
    public double readDouble() {
        return byteBuf.readDouble();
    }

    @Override
    public void writeBoolean(boolean data) {
        byteBuf.writeBoolean(data);
    }

    @Override
    public boolean readBoolean() {
        return byteBuf.readBoolean();
    }

    @Override
    public void writeUTF(String data) {
        byte[] bytes = data.getBytes(NetConstants.UTF8);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    @Override
    public String readUTF() {
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        return new String(bytes, NetConstants.UTF8);
    }

    @Override
    public int readerIndex() {
        return byteBuf.readerIndex();
    }

    @Override
    public void setReaderIndex(int readerIndex) {
        byteBuf.readerIndex(readerIndex);
    }

    @Override
    public int writerIndex() {
        return byteBuf.writerIndex();
    }

    @Override
    public void setWriterIndex(int writerIndex) {
        byteBuf.writerIndex(writerIndex);
    }
}
