package sync;

import com.jtemp.stage.net.netty.NettyByteBufferWrapper;
import com.jtemp.stage.net.protocol.ByteBufferWrapper;
import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/11/14 6:51 PM
 */
public class Request implements NetProtocol {

    String requestId;

    String content;

    public Request() {
    }

    @Override
    public int getCommandId() {
        return 0x0001;
    }

    public String getRequestId() {
        return requestId;
    }

    public Request setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Request setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public void encode(ByteBufferWrapper wrapper) {
        wrapper.writeUTF(getRequestId());
        wrapper.writeUTF(content);
    }

    @Override
    public void decode(NettyByteBufferWrapper wrapper) {
        requestId = wrapper.readUTF();
        content = wrapper.readUTF();
    }
}
