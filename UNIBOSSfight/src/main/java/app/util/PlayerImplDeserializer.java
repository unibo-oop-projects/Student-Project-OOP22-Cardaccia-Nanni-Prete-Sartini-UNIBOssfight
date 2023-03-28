package app.util;

import app.impl.component.TransformImpl;
import app.impl.entity.PlayerImpl;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class PlayerImplDeserializer implements JsonDeserializer<PlayerImpl> {
    @Override
    public PlayerImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return new PlayerImpl(
                        new Gson().fromJson(jsonObject.get("position"), TransformImpl.class),
                250,
                250,
                jsonObject.getAsJsonObject("renderer")
                        .get("filename")
                        .getAsString()
        );
    }
}
