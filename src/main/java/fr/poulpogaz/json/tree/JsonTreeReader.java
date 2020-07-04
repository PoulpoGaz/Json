package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.IJsonReader;
import fr.poulpogaz.json.JsonException;
import fr.poulpogaz.json.JsonReader;
import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Utility class for reading json encoded
 * streams into its equivalent representation as
 * a tree of {@link JsonElement}
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonTreeReader {

    /**
     * Reads the specified json based stream and
     * creates his equivalent representation as a
     * tree of {@link JsonElement}
     *
     * @param reader the json based stream
     * @return the json tree representation
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static JsonElement read(Reader reader) throws IOException, JsonException {
        return read(new JsonReader(reader));
    }

    /**
     * Reads the specified json based stream and
     * creates his equivalent representation as a
     * tree of {@link JsonElement}
     *
     * @param is the json based stream
     * @return the json tree representation
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static JsonElement read(InputStream is) throws IOException, JsonException {
        return read(new JsonReader(is));
    }
    
    /**
     * Reads the specified json based stream and
     * creates his equivalent representation as a
     * tree of {@link JsonElement}
     *
     * @implNote 
     * This method close the stream contrary to the
     * {@link #readImpl(IJsonReader)} method
     * 
     * @param reader the json based stream
     * @return the json tree representation
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static JsonElement read(IJsonReader reader) throws IOException, JsonException {
        JsonElement read = readImpl(reader);
        reader.close();

        return read;
    }

    /**
     * Reads the next data type in the specified
     * {@link IJsonReader}
     *
     * @param reader the json based stream
     * @return the json tree representation of the data type
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static JsonElement readImpl(IJsonReader reader) throws IOException, JsonException {
        if (reader.hasNextObject()) {
            return readObject(reader);
        } else if (reader.hasNextArray()) {
            return readArray(reader);
        } else {
            return readValue(reader);
        }
    }

    /**
     * Reads the next json object in the specified
     * {@link IJsonReader}
     *
     * @param reader the json based stream
     * @return the next json object as a {@link JsonObject}
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static JsonObject readObject(IJsonReader reader) throws IOException, JsonException {
        reader.beginObject();

        JsonObject object = new JsonObject();

        while (!reader.isObjectEnd()) {
            String key = reader.nextKey();
            JsonElement value = readImpl(reader);

            object.put(key, value);
        }

        reader.endObject();

        return object;
    }

    /**
     * Reads the next json array in the specified
     * {@link IJsonReader}
     *
     * @param reader the json based stream
     * @return the next json array as a {@link JsonArray}
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static JsonArray readArray(IJsonReader reader) throws IOException, JsonException {
        reader.beginArray();

        JsonArray array = new JsonArray();

        while (!reader.isArrayEnd()) {
            array.add(readImpl(reader));
        }

        reader.endArray();

        return array;
    }

    /**
     * Reads the next json value in the specified
     * {@link IJsonReader}
     *
     * @param reader the json based stream
     * @return the next json value as a {@link JsonValue}
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static JsonValue readValue(IJsonReader reader) throws IOException, JsonException {
        if (reader.hasNextString()) {
            return new JsonString(reader.nextString());
        } else if (reader.hasNextNumber()) {
            return new JsonNumber(reader.nextNumber());
        } else if (reader.hasNextBoolean()) {
            return JsonBoolean.of(reader.nextBoolean());
        } else {
            reader.nextNull();

            return JsonNull.INSTANCE;
        }
    }
}