package test;

import com.jtemp.stage.net.*;
import com.jtemp.stage.net.netty.NettyClient;
import com.jtemp.stage.net.protocol.NetProtocol;
import com.jtemp.stage.net.protocol.NetProtocolManager;

/**
 * @author ZMS
 * @Date 2018/11/8 2:22 PM
 */
public class NetClientTester {

    public static void main(String[] args) throws NetException {

        NetProtocolManager.regiester(PingPackage.COMMAND_ID, PingPackage.class);

        NetClient client = NetClientFactory.createClient(new NetClientHandler() {
            @Override
            public void channelActive(NettyClient client) {

            }

            @Override
            public void channelInactive(NettyClient client) {

            }

            @Override
            public void channelRead(NettyClient client, NetProtocol dataPackage) {

            }
        });

        client.connect(new NetUrl().setHost("127.0.0.1").setPort(8080));

        while (true) {
            client.send(new PingPackage());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
