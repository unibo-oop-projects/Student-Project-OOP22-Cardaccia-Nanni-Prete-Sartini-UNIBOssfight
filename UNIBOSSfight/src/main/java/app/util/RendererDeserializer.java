package app.util;

import com.google.gson.*;
import app.core.component.Renderer;
import app.impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public class RendererDeserializer implements JsonDeserializer<Renderer> {
    @Override
    public Renderer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        return new SpriteRenderer(
                jsonObject.get("height").getAsInt(),
                jsonObject.get("width").getAsInt(),
                new Gson().fromJson(jsonObject.get("color"), Color.class),
                jsonObject.get("filename").getAsString()
        );
    }
}
