package servers.ble.callbacks;

import servers.ble.BLEManager;
import com.welie.blessed.*;
import org.jetbrains.annotations.NotNull;

public class ManagerCallback extends BluetoothCentralManagerCallback {

    @Override
    public void onScanStarted() {
        super.onScanStarted();
        System.out.println("Started scan!!");
    }

    @Override
    public void onDiscoveredPeripheral(BluetoothPeripheral peripheral, ScanResult scanResult) {
        super.onDiscoveredPeripheral(peripheral, scanResult);

        //Startup phase, add picklets advertising service uuid
        System.out.println("Device discovered! " + scanResult);
        BLEManager.addPicklet(peripheral);
//        BLEManager.get().connectPeripheral(peripheral, new PeripheralCallback());

    }

    @Override
    public void onConnectionFailed(@NotNull BluetoothPeripheral peripheral, @NotNull BluetoothCommandStatus status) {
        super.onConnectionFailed(peripheral, status);
        System.out.println("failed to connect");
    }

    @Override
    public void onConnectedPeripheral(BluetoothPeripheral peripheral) {
        super.onConnectedPeripheral(peripheral);
        System.out.println("Connected!");

    }

    @Override
    public void onDisconnectedPeripheral(BluetoothPeripheral peripheral, BluetoothCommandStatus status) {
        super.onDisconnectedPeripheral(peripheral, status);
    }

}
