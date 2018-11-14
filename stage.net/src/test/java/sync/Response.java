package sync;

import com.jtemp.stage.net.protocol.NetProtocol;

/**
 * @author ZMS
 * @Date 2018/11/14 6:51 PM
 */
public class Response extends Request implements NetProtocol {
    @Override
    public int getCommandId() {
        return 0x000101;
    }
}
