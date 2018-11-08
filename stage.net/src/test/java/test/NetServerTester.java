package test;

import com.jtemp.stage.net.NetServer;
import com.jtemp.stage.net.NetServerFactory;
import com.jtemp.stage.net.NetServerHandler;
import com.jtemp.stage.net.protocol.NetConnection;
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
            public void channelRead(NetConnection connection, NetProtocol dataPackage) {
                if (dataPackage instanceof PingPackage) {
                    PingPackage pingPackage = (PingPackage)dataPackage;
                    pingPackage.setT1(System.currentTimeMillis());
                    connection.send(pingPackage);
                }
            }

            @Override
            public void channelActive(NetConnection connection) {
                System.out.println("active:" + connection);
            }

            @Override
            public void channelInactive(NetConnection connection) {
                System.out.println("inactive:" + connection);
            }
        });
        netServer.start(null, 8080);
        System.out.println("server is running ? " + netServer.isRunning());

    }

}
