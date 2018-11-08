package test;

import com.jtemp.stage.net.NetServer;
import com.jtemp.stage.net.NetServerFactory;
import com.jtemp.stage.net.NetServerHandler;
import com.jtemp.stage.net.netty.NettyConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import com.jtemp.stage.net.protocol.NetProtocolManager;

/**
 * @author ZMS
 * @Date 2018/11/8 2:17 PM
 */
public class NetServerTester {

    public static void main(String[] args) throws Exception {

        NetProtocolManager.regiester(PingPackage.COMMAND_ID, PingPackage.class);

        NetServer netServer = NetServerFactory.createTcpServer(new NetServerHandler() {
            @Override
            public void channelRead(NettyConnection connection, NetProtocol dataPackage) {
                System.out.println(dataPackage.toString());
            }

            @Override
            public void channelActive(NettyConnection connection) {

            }

            @Override
            public void channelInactive(NettyConnection connection) {

            }
        });
        netServer.start(8080);
        System.out.println("server is running ? " + netServer.isRunning());
        while (true) {
            Thread.sleep(1000);
        }
    }

}
