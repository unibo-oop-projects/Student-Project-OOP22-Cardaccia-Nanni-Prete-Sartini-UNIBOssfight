package app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import app.core.component.Renderer;
import app.core.entity.AbstractEntity;
import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.PlayerImpl;
import app.impl.level.LevelImpl;
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
    public LevelImpl loadLevel() {
        try {
            String json = readFile("output.json", StandardCharsets.UTF_8);

            GsonBuilder gsonBuilder = new GsonBuilder();

            JsonDeserializer<PlayerImpl> deserializer = new PlayerImplDeserializer(); // implementation detail
            JsonDeserializer<AbstractEntity> Edeserializer = new AbstractEntityDeserializer(); // implementation detail
            JsonDeserializer<Renderer> Rdeserializer = new RendererDeserializer(); // implementation detail


            gsonBuilder.registerTypeAdapter(Entity.class, Edeserializer);
            gsonBuilder.registerTypeAdapter(Renderer.class, Rdeserializer);
            gsonBuilder.registerTypeAdapter(PlayerImpl.class, deserializer);

            Gson customGson = gsonBuilder.create();
            LevelImpl customObject = customGson.fromJson(json, LevelImpl.class);

            return customObject;

        } catch (Exception e) {
            System.out.println(e);
            return null;
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
            e.printStackTrace();
        }
    }
}
