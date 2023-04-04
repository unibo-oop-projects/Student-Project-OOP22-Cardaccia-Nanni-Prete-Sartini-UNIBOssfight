package app.util;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * This class implements a Deserializer for the Entity interface.
 */
public class EntityDeserializer implements JsonDeserializer<Entity> {

    /**
     * Deserializes a json object extracted from an input file.
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context
     * @return the deserialized Entity
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
                        .create()
                        .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
        } catch (ClassNotFoundException e) {
            AppLogger.getLogger().severe("ERRORE: classe non trovata: " + e.getMessage());
            return null;
        }
    }
}
