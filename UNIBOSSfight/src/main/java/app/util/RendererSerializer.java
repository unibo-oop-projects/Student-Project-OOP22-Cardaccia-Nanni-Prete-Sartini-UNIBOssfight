package app.util;

import app.core.component.Renderer;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * This utility class is used to serialize the instances of Renderer.
 */
public class RendererSerializer implements JsonSerializer<Renderer> {
    /**
     * {@inheritDoc}
     */
        @Override
        public JsonElement serialize(
                final Renderer src,
                final Type typeOfSrc,
                final JsonSerializationContext context
        ) {
            final JsonObject jsonElement = (JsonObject) new GsonBuilder()
                    .create().toJsonTree(src);

            jsonElement.addProperty("className", src.getClass().getName());

            return jsonElement;
        }
}
