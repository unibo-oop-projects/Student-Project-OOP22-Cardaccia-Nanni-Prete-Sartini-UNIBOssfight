package util;

import com.google.gson.*;
import core.component.Renderer;
import impl.component.SpriteRenderer;
import javafx.scene.paint.Color;

import java.lang.reflect.Type;

public class RendererDeserializer implements JsonDeserializer<Renderer> {
    @Override
    public Renderer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        System.out.println("\n\n\n\nRenderer");
        System.out.println(jsonObject);


        return new SpriteRenderer(
                jsonObject.get("height").getAsInt(),
                jsonObject.get("width").getAsInt(),
                new Gson().fromJson(jsonObject.get("color"), Color.class),
                jsonObject.get("filename").getAsString()
        );
    }
}
