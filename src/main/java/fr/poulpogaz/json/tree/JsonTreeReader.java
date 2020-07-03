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

public class JsonTreeReader {

    public static JsonElement read(Reader reader) throws IOException, JsonException {
        return read(new JsonReader(reader));
    }

    public static JsonElement read(InputStream is) throws IOException, JsonException {
        return read(new JsonReader(is));
    }

    public static JsonElement read(IJsonReader reader) throws IOException, JsonException {
        JsonElement read = readImpl(reader);
        reader.close();

        return read;
    }

    private static JsonElement readImpl(IJsonReader reader) throws IOException, JsonException {
        if (reader.hasNextObject()) {
            return readObject(reader);
        } else if (reader.hasNextArray()) {
            return readArray(reader);
        } else {
            return readValue(reader);
        }
    }

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

    private static JsonArray readArray(IJsonReader reader) throws IOException, JsonException {
        reader.beginArray();

        JsonArray array = new JsonArray();

        while (!reader.isArrayEnd()) {
            array.add(readImpl(reader));
        }

        reader.endArray();

        return array;
    }

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