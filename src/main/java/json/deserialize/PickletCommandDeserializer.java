package json.deserialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import servers.ble.PickletCommand;

import java.lang.reflect.Type;

public class PickletCommandDeserializer implements JsonDeserializer<PickletCommand> {
    @Override
    public PickletCommand deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String command = json.getAsJsonObject().get("command").getAsString();
        String address = json.getAsJsonObject().get("address").getAsString();
        int red = json.getAsJsonObject().get("red").getAsInt();
        int blue = json.getAsJsonObject().get("blue").getAsInt();
        int green = json.getAsJsonObject().get("green").getAsInt();
        int yellow = json.getAsJsonObject().get("yellow").getAsInt();

        PickletCommand pickletCommand = new PickletCommand();
        pickletCommand.setCommand(command);
        pickletCommand.setAddress(address);
        pickletCommand.setRed(red);
        pickletCommand.setBlue(blue);
        pickletCommand.setGreen(green);
        pickletCommand.setYellow(yellow);

        return pickletCommand;

    }
}
