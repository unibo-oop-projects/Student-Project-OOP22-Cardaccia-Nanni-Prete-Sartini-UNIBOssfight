package app.util;

import app.core.component.Transform;
import app.impl.component.TransformImpl;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * This class implements a Deserializer for the Transform interface.
 */
public class TransformDeserializer implements JsonDeserializer<Transform> {

    /**
     * Deserializes a json object extracted from an input file.
     *
     * @param json    The Json data being deserialized
     * @param typeOfT The type of the Object to deserialize to
     * @param context
     * @return the deserialized Transform
     * @throws JsonParseException
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
