package app.util;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.component.TransformImpl;
import app.impl.entity.PlayerImpl;
import app.impl.level.LevelImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {
    private String readFile(String path, Charset encoding) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    public LevelImpl loadLevel() throws Exception {
        try {
            String json = readFile("output.json", StandardCharsets.UTF_8);

            GsonBuilder gsonBuilder = new GsonBuilder();

            JsonDeserializer<Renderer> Rdeserializer = new RendererDeserializer(); // implementation detail

            JsonDeserializer<Transform> TDeserializer = (json12, typeOfT, context) -> {
                JsonObject jsonObject = json12.getAsJsonObject();
                    return new Gson().fromJson(jsonObject, TransformImpl.class);
            };

            JsonDeserializer<AbstractEntity> EntityDeserializer = (json12, typeOfT, context) -> {
                JsonObject jsonObject = json12.getAsJsonObject();
                try {
                    return (AbstractEntity) new GsonBuilder()
                            .registerTypeAdapter(Transform.class, TDeserializer)
                            .registerTypeAdapter(Renderer.class, Rdeserializer)
                            .create()
                            .fromJson(jsonObject, Class.forName(jsonObject.get("className").getAsString()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

            gsonBuilder.registerTypeAdapter(Entity.class, EntityDeserializer);
            gsonBuilder.registerTypeAdapter(PlayerImpl.class, EntityDeserializer);

            Gson customGson = gsonBuilder.create();

            return customGson.fromJson(json, LevelImpl.class);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void serializeLevel(final Level level) {
        try {
            String jsonString = new GsonBuilder()
                    //.excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting()
                    .create()
                    .toJson(level);

            FileWriter file = new FileWriter("output.json");
            file.write(jsonString);
            file.close();
        } catch (Exception e) {
            AppLogger.getLogger().severe(e.getMessage());
        }
    }
}
