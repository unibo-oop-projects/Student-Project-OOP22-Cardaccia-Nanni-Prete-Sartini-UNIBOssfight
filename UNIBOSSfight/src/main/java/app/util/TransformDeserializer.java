package app.util;

import app.core.component.Transform;
import app.impl.component.TransformImpl;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;

/**
 * This utility class implements a Deserializer for the Transform interface.
 */
public class TransformDeserializer implements JsonDeserializer<Transform> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Transform deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) {
        final JsonObject jsonObject = json.getAsJsonObject();
        return new Gson().fromJson(jsonObject, TransformImpl.class);
    }
}
