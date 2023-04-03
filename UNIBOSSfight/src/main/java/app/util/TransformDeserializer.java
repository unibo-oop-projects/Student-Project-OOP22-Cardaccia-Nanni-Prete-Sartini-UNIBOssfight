package app.util;

import app.core.component.Transform;
import app.impl.component.TransformImpl;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TransformDeserializer implements JsonDeserializer<Transform> {
    @Override
    public Transform deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return new Gson().fromJson(jsonObject, TransformImpl.class);
    }
}
