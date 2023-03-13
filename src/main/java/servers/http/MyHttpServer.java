package servers.http;

import com.sun.net.httpserver.HttpServer;
import servers.http.handlers.BLEHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MyHttpServer  {
    HttpServer server;

    public MyHttpServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new BLEHandler());
        server.setExecutor(null);
        server.start();

    }

    public HttpServer getServer() {
        return server;
    }

}
