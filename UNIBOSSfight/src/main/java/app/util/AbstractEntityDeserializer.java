package app.util;

import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.impl.component.TransformImpl;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class AbstractEntityDeserializer implements JsonDeserializer<AbstractEntity> {
    @Override
    public AbstractEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        try {
            return (AbstractEntity) Class.forName(jsonObject.get("className").getAsString())
                    .getConstructor(
                            Transform.class,
                            int.class,
                            int.class,
                            String.class
                    ).newInstance(
                    new Gson().fromJson(jsonObject.get("position"), TransformImpl.class),
                    jsonObject.get("height").getAsInt(),
                    jsonObject.get("width").getAsInt(),
                    jsonObject.getAsJsonObject("renderer").get("filename").getAsString()
            );


        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
