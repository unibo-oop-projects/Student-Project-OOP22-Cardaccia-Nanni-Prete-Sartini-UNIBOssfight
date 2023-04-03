package app.util;

import app.core.component.Renderer;
import app.core.component.Transform;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.component.TransformImpl;
import app.impl.entity.Player;
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
    private String readFile(String path) throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public LevelImpl loadLevel(String jsonFile) throws Exception {
        try {
            String json = readFile(jsonFile);

            GsonBuilder gsonBuilder = new GsonBuilder();

            JsonDeserializer<Entity> EntityDeserializer = new EntityDeserializer();

            gsonBuilder.registerTypeAdapter(Entity.class, EntityDeserializer);
            gsonBuilder.registerTypeAdapter(Player.class, EntityDeserializer);

            return gsonBuilder.create().fromJson(json, LevelImpl.class);

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void serializeLevel(final Level level) {
        try {
            String jsonString = new GsonBuilder()
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
