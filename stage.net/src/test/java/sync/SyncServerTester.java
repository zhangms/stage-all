package sync;

import com.jtemp.stage.net.NetServer;
import com.jtemp.stage.net.NetServerFactory;
import com.jtemp.stage.net.NetServerHandler;
import com.jtemp.stage.net.protocol.NetConnection;
import com.jtemp.stage.net.protocol.NetProtocol;
import com.jtemp.stage.net.protocol.NetProtocolManager;

import java.util.concurrent.Future;

/**
 * @author ZMS
 * @Date 2018/11/14 6:50 PM
 */
public class SyncServerTester {

    static class SyncNetServerHandler implements NetServerHandler {

        @Override
        public void channelRead(NetConnection connection, NetProtocol dataPackage) {
            if (dataPackage instanceof Request) {
                Request request = (Request)dataPackage;
                Response response = new Response();
                response.setRequestId(request.getRequestId())
                    .setContent("hello " + request.getContent());
                connection.send(response);
                System.out.println("responsed:" + request.getRequestId());
            } else {
                System.out.println(dataPackage);
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

        @Override
        public Future<NetProtocol> wrapRequestFuture(NetProtocol dataPackage) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Request request = new Request();
        Response response = new Response();
        NetProtocolManager.regiester(request.getCommandId(), request.getClass());
        NetProtocolManager.regiester(response.getCommandId(), response.getClass());
        NetServer netServer = NetServerFactory.createTcpServer(new SyncNetServerHandler());
        netServer.start(null, 8080);
        while (true) {
            Thread.sleep(1111);
        }
    }

}
