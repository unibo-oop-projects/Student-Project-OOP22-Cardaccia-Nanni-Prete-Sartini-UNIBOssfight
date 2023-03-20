package util;

import com.google.gson.*;
import core.component.Renderer;
import core.component.Transform;
import core.entity.AbstractEntity;

import java.lang.reflect.Type;

public class AbstractEntityDeserializer implements JsonDeserializer<AbstractEntity> {
    @Override
    public AbstractEntity deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        try {
            System.out.println(Class.forName(jsonObject.get("className").getAsString()).getConstructors()[0]);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            return (AbstractEntity) Class.forName(jsonObject.get("className").getAsString())
                    .getConstructor(
                            Transform.class,
                            int.class,
                            int.class,
                            String.class
                    ).newInstance(
                    new Gson().fromJson(jsonObject.get("position"), Transform.class),
                    jsonObject.get("height").getAsInt(),
                    jsonObject.get("width").getAsInt(),
                    jsonObject.getAsJsonObject("renderer").get("filename").getAsString()
            );


        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
