package app.util;

import app.core.entity.Entity;
import app.core.level.Level;
import app.game.Score;
import app.impl.entity.Player;
import app.impl.level.BossLevel;
import app.impl.level.LevelImpl;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is used to serialize and deserializer levels.
 */
public class DataManager {
    private final String userHome = System.getProperty("user.home");
    private final String separator = File.separator;

    /**
     * This method get the content of the json file to load.
     *
     * @param path the path to the file to load
     * @return the json string of the level.
     * @throws IOException if an error with the file occurs
     */
    private String readFile(final String path) throws IOException {
        byte[] encoded = new byte[0];
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(path)) {
            if (is != null) {
                encoded = is.readAllBytes();
            }
        }

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
     * @param filename for the output
     */
    public void serializeLevel(final Level level, final String filename) throws IOException {
        final String jsonString = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Entity.class, new EntitySerializer())
                .registerTypeAdapter(Player.class, new EntitySerializer())
                .create()
                .toJson(level);

        try (FileWriter file = new FileWriter(filename, StandardCharsets.UTF_8)) {
            file.write(jsonString);
        } catch (IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw new IOException(e);
        }
    }

    /**
     * Serializes an instance of the Score class.
     *
     * @param score to deserialize
     * @throws IOException input output exception
     */
    public void serializeScore(final Score score) throws IOException {
        final String jsonString = new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(score);

        try {
            final String file = userHome + separator + "score.json";

            final PrintWriter writer = new PrintWriter(file, StandardCharsets.UTF_8);
            writer.print(jsonString);
            writer.close();

        } catch (IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            throw new IOException(e);
        }
    }

    /**
     * Loads a Score from the given json file.
     *
     * @param jsonFile the name of the file that contains the level
     * @return the scores of the level
     * @throws IOException input output exception
     */
    public Score deserializeScore(final String jsonFile) throws IOException {
        String json;
        try {
            final byte[] bytes = Files.readAllBytes(
                    Paths.get(userHome + separator + jsonFile));
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (final IOException e) {
            AppLogger.getLogger().severe(e.getMessage());
            json = readFile(jsonFile);
        }

        return new GsonBuilder().create().fromJson(json, Score.class);
    }
}
