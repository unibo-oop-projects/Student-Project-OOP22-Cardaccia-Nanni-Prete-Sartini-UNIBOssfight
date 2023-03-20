package util;

import com.google.gson.*;
import core.component.Transform;
import impl.entity.PlayerImpl;
import javafx.geometry.Point2D;

import java.lang.reflect.Type;

public class PlayerImplDeserializer implements JsonDeserializer<PlayerImpl> {
    @Override
    public PlayerImpl deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("\n\n\n\nPlayer");
        System.out.println(jsonObject.getAsJsonObject("position").get("position"));


        return new PlayerImpl(
                        new Gson().fromJson(jsonObject.get("position"), Transform.class),
                250,
                250,
                "guido"
        );
    }
}
