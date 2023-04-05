package app.util;

import app.core.entity.Entity;
import app.core.level.Level;
import app.impl.entity.Player;
import app.impl.level.BossLevel;
import app.impl.level.LevelImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is used to serialize and deserializer levels.
 */
public class DataManager {
    /**
     * This method get the content of the json file to load.
     *
     * @param path the path to the file to load
     * @return the json string of the level.
     * @throws IOException if an error with the file occurs
     */
    private String readFile(final String path) throws IOException {
        final byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /**
     * Loads a level from the given json file.
     *
     * @param jsonFile the name of the file that contains the level
     * @return a level without a boss
     * @throws IOException input output exception
     */
    public LevelImpl loadLevel(final String jsonFile) throws IOException {
        String json;
        try {
            json = readFile(jsonFile);
        } catch (IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw new IOException(e);
        }

        return createGsonBuilder().create().fromJson(json, LevelImpl.class);
    }

    /**
     * Loads a level from the given json file.
     *
     * @param jsonFile the name of the file to load
     * @return a level without a boss
     * @throws IOException input output exception
     */
    public LevelImpl loadBossLevel(final String jsonFile) throws IOException {
        String json;
        try {
            json = readFile(jsonFile);
        } catch (IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw new IOException(e);
        }

        return createGsonBuilder().create().fromJson(json, BossLevel.class);
    }

    /**
     * creates the GsonBuilder.
     *
     * @return the Gsonbuilder used to deserialize levels
     */
    private GsonBuilder createGsonBuilder() {
        final GsonBuilder gsonBuilder = new GsonBuilder();

        final JsonDeserializer<Entity> entityDeserializer = new EntityDeserializer();

        gsonBuilder.registerTypeAdapter(Entity.class, entityDeserializer);
        gsonBuilder.registerTypeAdapter(Player.class, entityDeserializer);

        return gsonBuilder;
    }

    /**
     * Serializes the level and saves it in a json file.
     *
     * @param level the level to serialize
     */
    public void serializeLevel(final Level level) throws IOException {
        final String jsonString = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(level);

        final FileWriter file;
        try {
            file = new FileWriter("output.json");
            file.write(jsonString);
            file.close();
        } catch (IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw new IOException(e);
        }
    }
}
