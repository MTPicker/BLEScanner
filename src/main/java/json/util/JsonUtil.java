package json.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class JsonUtil {
    public static <T> T fromJson(String json, Class<T> type, JsonDeserializer<T> deserializer) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(type, deserializer);
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, type);
    }

    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
