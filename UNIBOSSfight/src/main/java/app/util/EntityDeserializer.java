package app.util;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import com.google.gson.*;

import java.lang.reflect.Type;

public class EntityDeserializer implements JsonDeserializer<Entity> {

    @Override
    public Entity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        JsonDeserializer<Renderer> rendererDeserializer = new RendererDeserializer();
        JsonDeserializer<Transform> transformDeserializer = new TransformDeserializer();

        try {
            return (AbstractEntity) new GsonBuilder()
                    .registerTypeAdapter(Transform.class, transformDeserializer)
                    .registerTypeAdapter(Renderer.class, rendererDeserializer)
                    .create()
                    .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
