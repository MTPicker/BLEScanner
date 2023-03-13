Uses a http server on on port 8000 when running the application

takes json data in the format of at the end point /test
(full url in this example: http://localhost:8000/test)

{
    "address": "34:85:18:25:3F:FA",
    "command": "ON",
    "red": 1,
    "blue": 0,
    "green": 1,
    "yellow": 0
}

The endpoint will add a connection to the BLEManager connection queue (the queue part doen't really work rn)

The BLEManager will process the connections and connect to picklets via scanning for it's service, connecting to the
device and writing the data to the device.

in the resource folder there is a shellscript that will make life easier for sending http posts with
curl. 

examples 

./sendPickletCommand.sh "34:85:18:25:3F:FA" "ON" 1 1 0 0
./sendPickletCommand.sh "58:CF:79:F0:A1:7A" "ON" 1 1 0 0



