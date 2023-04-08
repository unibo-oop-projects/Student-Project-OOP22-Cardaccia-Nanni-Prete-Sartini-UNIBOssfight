package app.util;

import app.core.component.Renderer;
import app.core.entity.Entity;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * This utility class is used to serialize the instances of Entity.
 */
public class EntitySerializer implements JsonSerializer<Entity> {
    /**
     * {@inheritDoc}
     */
        @Override
        public JsonElement serialize(
                final Entity src,
                final Type typeOfSrc,
                final JsonSerializationContext context
        ) {
            final JsonObject jsonElement = (JsonObject) new GsonBuilder()
                    .registerTypeAdapter(Renderer.class, new RendererSerializer())
                    .create().toJsonTree(src);

            jsonElement.addProperty("className", src.getType());

            return jsonElement;
        }
}
