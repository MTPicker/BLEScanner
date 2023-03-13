package servers.ble;

import com.welie.blessed.BluetoothCentralManager;
import com.welie.blessed.BluetoothPeripheral;
import com.welie.blessed.ConnectionState;
import org.jetbrains.annotations.NotNull;
import servers.ble.callbacks.ManagerCallback;
import servers.ble.callbacks.PeripheralCallback;

import java.util.*;
import java.util.concurrent.Semaphore;

public class BLEManager {
    static BluetoothCentralManager centralManager;
    static List<BluetoothPeripheral> peripheralList;
    static PeripheralCallback peripheralCallback;

    static Queue<Connection> connectionQueue;
    static Semaphore semaphore = new Semaphore(1);
    public static byte[] dataValue;

    public static void startup() {
        centralManager = new BluetoothCentralManager(new ManagerCallback());
        peripheralList = new ArrayList<BluetoothPeripheral>();
        connectionQueue = new ArrayDeque<>();
        peripheralCallback = new PeripheralCallback();
        dataValue = new byte[1];

        // start scan
        centralManager.scanForPeripheralsWithServices(PickletID.uuids);

        // schedule stopScan() to be called after 5 seconds
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                centralManager.stopScan();
            }
        }, 5000); // 5000 milliseconds = 5 seconds
    }

    //this method connects to picklet
    public static void connect(@NotNull PickletCommand pickletCommand) {
        BluetoothPeripheral peripheral = findPickletByAddress(pickletCommand.getAddress());
        byte[] data = new byte[1];
        data[0] = (byte) LedPattern.getPattern(pickletCommand);
        int size = connectionQueue.size();

        //if peripheral exists then add to connection que
        if (peripheral != null) {
            connectionQueue.add(new Connection(peripheral, data));
        }

        if (size == 0) {
            processConnections();
        }

    }

    public static void processConnections() {
        if (semaphore.tryAcquire()) {
            try {
                Connection connection;

                while (!connectionQueue.isEmpty()) {

                    if(!hasConnection()) {
                        connection = connectionQueue.poll();
                        dataValue = connection.data;

                        centralManager.autoConnectPeripheral(connection.peripheral, peripheralCallback);
                    }

                }
            } finally {
                semaphore.release();
            }
        }
    }

    //checks the state of peripheral list to see if any have a connection
    static private boolean hasConnection() {
        return peripheralList
                .stream()
                .anyMatch(peripheral -> peripheral.getState().equals(ConnectionState.CONNECTED)
                        || peripheral.getState().equals(ConnectionState.CONNECTING)
                        || peripheral.getState().equals(ConnectionState.DISCONNECTING));
    }


    public static int addPicklet(BluetoothPeripheral peripheral) {

        //add picklet to list if not in list
        if (!peripheralList.contains(peripheral)) {
            peripheralList.add(peripheral);
        } else {
            System.out.println("Picklet already exist");
        }
        return 0;
    }

    public static BluetoothPeripheral findPickletByAddress(String address) {
        BluetoothPeripheral peripheral = peripheralList.stream()
                .filter(x -> x.getAddress().equals(address))
                .findFirst()
                .get();

        if (peripheral != null) {
            return peripheral;
        } else {
            System.out.println("Cant find picklet from list");
        }
        return null;
    }


    public static BluetoothCentralManager get() {
        return centralManager;
    }
}
