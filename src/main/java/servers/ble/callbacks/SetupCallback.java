package servers.ble.callbacks;

import com.welie.blessed.BluetoothCentralManagerCallback;
import com.welie.blessed.BluetoothCommandStatus;
import com.welie.blessed.BluetoothPeripheral;
import com.welie.blessed.ScanResult;
import org.jetbrains.annotations.NotNull;

public class SetupCallback extends BluetoothCentralManagerCallback {
    public SetupCallback() {

    }

    @Override
    public void onScanStarted() {
        super.onScanStarted();
        System.out.println("Started scan!!");
    }

    @Override
    public void onDiscoveredPeripheral(BluetoothPeripheral peripheral, ScanResult scanResult) {
        super.onDiscoveredPeripheral(peripheral, scanResult);

        System.out.println("Device discovered! " + scanResult);

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
