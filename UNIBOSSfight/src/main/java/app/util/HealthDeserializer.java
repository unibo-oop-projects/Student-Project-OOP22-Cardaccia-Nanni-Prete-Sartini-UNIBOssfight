package app.util;

import app.core.component.Health;
import app.impl.component.HealthImpl;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.lang.reflect.Type;

/**
 * This utility class implements a Deserializer for the Health interface.
 */
public class HealthDeserializer implements JsonDeserializer<Health> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Health deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) {
        final JsonObject jsonObject = json.getAsJsonObject();
        return new Gson().fromJson(jsonObject, HealthImpl.class);
    }
}
