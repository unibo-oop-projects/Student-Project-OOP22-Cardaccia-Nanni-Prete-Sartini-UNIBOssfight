package app.util;

import app.core.component.Health;
import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.impl.component.HealthImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * This utility class implements a Deserializer for the Entity interface.
 */
public class EntityDeserializer implements JsonDeserializer<Entity> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) {
        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonDeserializer<Renderer> rendererDeserializer = new RendererDeserializer();
        final JsonDeserializer<Transform> transformDeserializer = new TransformDeserializer();

        try {
            return (AbstractEntity) new GsonBuilder()
                        .registerTypeAdapter(Transform.class, transformDeserializer)
                        .registerTypeAdapter(Renderer.class, rendererDeserializer)
                    .registerTypeAdapter(Health.class, (JsonDeserializer<Health>) (json1, typeOfT1, context1) -> {
                        final JsonObject jsonObject1 = json1.getAsJsonObject();
                        return new Gson().fromJson(jsonObject1, HealthImpl.class);
                    })
                        .create()
                        .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
        } catch (ClassNotFoundException e) {
            AppLogger.getLogger().severe("ERRORE: classe non trovata: " + e.getMessage());
            return null;
        }
    }
}
