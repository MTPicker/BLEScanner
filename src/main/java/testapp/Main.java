package testapp;

import servers.ble.BLEManager;
import servers.http.MyHttpServer;

public class Main {
    static BLEManager bleManager;
    public static void main(String[] args) throws Exception {
        System.out.println("Running server");
        MyHttpServer myHttpServer = new MyHttpServer();

        System.out.println("BLE Server Running");
        BLEManager.startup();
    }
}
