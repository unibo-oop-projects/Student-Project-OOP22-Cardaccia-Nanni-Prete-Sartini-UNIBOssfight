package app.util;

import app.core.component.Renderer;
import app.impl.component.RendererImpl;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * This class implements a Deserializer for the Renderer interface.
 */
public class RendererDeserializer implements JsonDeserializer<Renderer> {

    /**
     * Deserializes a json object extracted from an input file.
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context
     * @return the deserialized Renderer
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
