package app.util;

import app.impl.component.RendererImpl;
import com.google.gson.*;
import app.core.component.Renderer;

import java.lang.reflect.Type;

public class RendererDeserializer implements JsonDeserializer<Renderer> {
    @Override
    public Renderer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        try {
            return (RendererImpl) new GsonBuilder()
                    .create()
                    .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
