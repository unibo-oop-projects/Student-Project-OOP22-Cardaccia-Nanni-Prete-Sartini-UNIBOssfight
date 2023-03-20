package util;

import com.google.gson.*;
import core.component.Renderer;
import core.component.Transform;
import core.entity.AbstractEntity;
import impl.entity.PlayerImpl;
import impl.entity.TmpEntityImpl;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public class AbstractEntityDeserializer implements JsonDeserializer<AbstractEntity> {
    @Override
    public AbstractEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return new TmpEntityImpl(
                new Gson().fromJson(jsonObject.get("position"), Transform.class),
                jsonObject.get("height").getAsInt(),
                jsonObject.get("width").getAsInt(),
                jsonObject.get("filename").getAsString()
        );
    }
}
