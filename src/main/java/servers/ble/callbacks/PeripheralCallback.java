package servers.ble.callbacks;

import com.welie.blessed.*;
import org.jetbrains.annotations.NotNull;
import servers.ble.BLEManager;
import servers.ble.PickletID;

import java.util.List;

public class PeripheralCallback extends BluetoothPeripheralCallback {

    @Override
    public void onServicesDiscovered(@NotNull BluetoothPeripheral peripheral, @NotNull List<BluetoothGattService> services) {
        super.onServicesDiscovered(peripheral, services);
        //write data to device
        peripheral.writeCharacteristic(
                PickletID.pickletService,
                PickletID.pickletChar,
                BLEManager.dataValue,
                BluetoothGattCharacteristic.WriteType.WITH_RESPONSE);

    }

    @Override
    public void onCharacteristicWrite(@NotNull BluetoothPeripheral peripheral, @NotNull byte[] value, @NotNull BluetoothGattCharacteristic characteristic, @NotNull BluetoothCommandStatus status) {
        peripheral.cancelConnection();
    }

}
