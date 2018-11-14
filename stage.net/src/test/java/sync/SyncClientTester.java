package sync;

import com.jtemp.stage.net.*;
import com.jtemp.stage.net.netty.NettyClient;
import com.jtemp.stage.net.protocol.NetProtocol;
import com.jtemp.stage.net.protocol.NetProtocolManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author ZMS
 * @Date 2018/11/14 7:01 PM
 */
public class SyncClientTester {

    static class SyncNetClientHandler implements NetClientHandler {

        private Map<String, CompletableFuture<NetProtocol>> futureMap = Collections.synchronizedMap(new HashMap<>());

        @Override
        public void channelActive(NettyClient client) {

        }

        @Override
        public void channelInactive(NettyClient client) {

        }

        @Override
        public void channelRead(NettyClient client, NetProtocol dataPackage) {
            CompletableFuture<NetProtocol> future = futureMap.remove(((Request)dataPackage).getRequestId());
            future.complete(dataPackage);
        }

        @Override
        public Future<NetProtocol> wrapRequestFuture(NetProtocol dataPackage) {
            CompletableFuture<NetProtocol> future = new CompletableFuture<>();
            futureMap.put(((Request)dataPackage).getRequestId(), future);
            return future;
        }
    }

    public static void main(String[] args)
        throws NetException, ExecutionException, InterruptedException, TimeoutException {
        Request request = new Request();
        Response response = new Response();
        NetProtocolManager.regiester(request.getCommandId(), request.getClass());
        NetProtocolManager.regiester(response.getCommandId(), response.getClass());
        NetClient client = NetClientFactory.createClient(new SyncNetClientHandler());
        client.connect(new NetUrl().setHost("localhost").setPort(8080));

        Future<NetProtocol> future = client.futureInvoke(
            new Request().setRequestId(UUID.randomUUID().toString()).setContent("张三"));
        Response res = (Response)future.get(10, TimeUnit.SECONDS);
        //Response res = (Response)future.get();
        System.out.println(res.getContent());
    }

}
