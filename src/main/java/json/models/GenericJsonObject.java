package json.models;

import com.google.gson.JsonObject;

public class GenericJsonObject {
    //TODO Make it to handle multiple key val instead of one pair
    public static JsonObject create(String key, String value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(key, value);
        return  jsonObject;
    }

}
