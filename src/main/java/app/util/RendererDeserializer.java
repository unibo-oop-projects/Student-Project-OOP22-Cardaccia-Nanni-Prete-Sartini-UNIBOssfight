package app.util;

import app.core.component.Renderer;
import app.impl.component.RendererImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;

/**
 * This utility class implements a Deserializer for the Renderer interface.
 */
public class RendererDeserializer implements JsonDeserializer<Renderer> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Renderer deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) {
        final JsonObject jsonObject = json.getAsJsonObject();
        try {
            return (RendererImpl) new GsonBuilder()
                    .create()
                    .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
        } catch (ClassNotFoundException e) {
            AppLogger.getLogger().severe("ERRORE: classe non trovata: " + e.getMessage());
            return null;
        }
    }
}
