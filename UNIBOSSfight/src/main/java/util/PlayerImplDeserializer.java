package util;

import com.google.gson.*;
import impl.component.TransformImpl;
import impl.entity.PlayerImpl;

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
