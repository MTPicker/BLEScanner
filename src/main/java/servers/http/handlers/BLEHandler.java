package servers.http.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import json.deserialize.PickletCommandDeserializer;
import json.models.GenericJsonObject;
import json.util.JsonUtil;
import servers.ble.BLEManager;
import servers.ble.PickletCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;


public class BLEHandler implements HttpHandler {
    //Forwards the json data to BLEManager
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        StringBuilder buffer = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        }

        String data = buffer.toString();

        // Do something with the request data
        System.out.println("Data: " + data);

        PickletCommand pickletCommand = JsonUtil.fromJson(data, PickletCommand.class, new PickletCommandDeserializer());

        BLEManager.connect(pickletCommand);

        // Serialize the JSON object or array into a JSON string
        String response  = JsonUtil.toJson(GenericJsonObject.create("status", "success"));

        // Set the response headers and body to return the JSON string
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

}
