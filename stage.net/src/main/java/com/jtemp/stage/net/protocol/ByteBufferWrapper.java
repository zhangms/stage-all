package com.jtemp.stage.net.protocol;

/**
 * @author ZMS
 * @Date 2018/10/20 4:32 PM
 */
public interface ByteBufferWrapper {

    void writeByte(byte data);

    byte readByte();

    void writeInt(int data);

    void writeBytes(byte[] data);

    int readableBytes();

    int readInt();

    void readBytes(byte[] data);

    void writeLong(long data);

    long readLong();

    void writeFloat(float data);

    float readFloat();

    void writeDouble(double data);

    double readDouble();

    void writeBoolean(boolean data);

    boolean readBoolean();

    void writeUTF(String data);

    String readUTF();

    int readerIndex();

    void setReaderIndex(int readerIndex);

    int writerIndex();

    void setWriterIndex(int writerIndex);
}
