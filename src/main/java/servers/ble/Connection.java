package servers.ble;

import com.welie.blessed.BluetoothPeripheral;

public class Connection {

    public BluetoothPeripheral peripheral;
    public byte[] data;

    public Connection(BluetoothPeripheral peripheral, byte[] data) {
        this.peripheral = peripheral;
        this.data = data;
    }
}
