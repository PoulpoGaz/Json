package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.IJsonWriter;
import fr.poulpogaz.json.JsonException;
import fr.poulpogaz.json.JsonPrettyWriter;
import fr.poulpogaz.json.tree.value.JsonNumber;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

/**
 * Utility class for writing a {@link JsonElement}
 * in a stream.
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonTreeWriter {

    /**
     * Writes the specified {@link JsonElement}
     *
     * @param element the json element to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonElement element, Writer writer) throws IOException, JsonException {
        if (element.isObject()) {
            write((JsonObject) element, new JsonPrettyWriter(writer));
        } else if (element.isArray()) {
            write((JsonArray) element, new JsonPrettyWriter(writer));
        } else if (element.isValue()) {
            write((JsonValue) element, new JsonPrettyWriter(writer));
        }
    }

    /**
     * Writes the specified {@link JsonObject}
     *
     * @param object the json object to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonObject object, Writer writer) throws IOException, JsonException {
        write(object, new JsonPrettyWriter(writer));
    }

    /**
     * Writes the specified {@link JsonArray}
     *
     * @param array the json array to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonArray array, Writer writer) throws IOException, JsonException {
        write(array, new JsonPrettyWriter(writer));
    }

    /**
     * Writes the specified {@link JsonValue}
     *
     * @param value the json value to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonValue value, Writer writer) throws IOException, JsonException {
        write(value, new JsonPrettyWriter(writer));
    }

    /**
     * Writes the specified {@link JsonElement}
     *
     * @param element the json element to be written
     * @param os the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonElement element, OutputStream os) throws IOException, JsonException {
        if (element.isObject()) {
            write((JsonObject) element, new JsonPrettyWriter(os));
        } else if (element.isArray()) {
            write((JsonArray) element, new JsonPrettyWriter(os));
        } else if (element.isValue()) {
            write((JsonValue) element, new JsonPrettyWriter(os));
        }
    }

    /**
     * Writes the specified {@link JsonObject}
     *
     * @param object the json object to be written
     * @param os the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonObject object, OutputStream os) throws IOException, JsonException {
        write(object, new JsonPrettyWriter(os));
    }

    /**
     * Writes the specified {@link JsonArray}
     *
     * @param array the json array to be written
     * @param os the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonArray array, OutputStream os) throws IOException, JsonException {
        write(array, new JsonPrettyWriter(os));
    }

    /**
     * Writes the specified {@link JsonValue}
     *
     * @param value the json value to be written
     * @param os the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonValue value, OutputStream os) throws IOException, JsonException {
        write(value, new JsonPrettyWriter(os));
    }

    /**
     * Writes the specified {@link JsonElement}
     *
     * @implNote
     * This method close the stream contrary to the
     * {@link #writeImpl(JsonElement, IJsonWriter)} method
     *
     * @param element the json element to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonElement element, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(element, writer);
        writer.close();
    }

    /**
     * Writes the specified {@link JsonObject}
     *
     * @implNote
     * This method close the stream contrary to the
     * {@link #writeImpl(JsonObject, IJsonWriter)} method
     *
     * @param object the json object to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonObject object, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(object, writer);
        writer.close();
    }

    /**
     * Writes the specified {@link JsonArray}
     *
     * @implNote
     * This method close the stream contrary to the
     * {@link #writeImpl(JsonArray, IJsonWriter)} method
     *
     * @param array the json array to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonArray array, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(array, writer);
        writer.close();
    }

    /**
     * Writes the specified {@link JsonValue}
     *
     * @implNote
     * This method close the stream contrary to the
     * {@link #writeImpl(JsonValue, IJsonWriter)} method
     *
     * @param value the json value to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    public static void write(JsonValue value, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(value, writer);
        writer.close();
    }

    /**
     * Writes the specified {@link JsonElement}
     *
     * @implNote
     * This method redirects to {@link #writeImpl(JsonObject, IJsonWriter)
     * or {@link #writeImpl(JsonArray, IJsonWriter)} or {@link #writeImpl(JsonObject, IJsonWriter)}
     * depending of the type of the specified element
     *
     * @param element the json element to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static void writeImpl(JsonElement element, IJsonWriter writer) throws IOException, JsonException {
        if (element.isObject()) {
            writeImpl((JsonObject) element, writer);
        } else if (element.isArray()) {
            writeImpl((JsonArray) element, writer);
        } else if (element.isValue()) {
            writeImpl((JsonValue) element, writer);
        }
    }

    /**
     * Writes the specified {@link JsonObject}
     *
     * @param object the json object to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static void writeImpl(JsonObject object, IJsonWriter writer) throws IOException, JsonException {
        writer.beginObject();

        for (Map.Entry<String, JsonElement> element : object.entrySet()) {
            writer.key(element.getKey());
            writeImpl(element.getValue(), writer);
        }

        writer.endObject();
    }

    /**
     * Writes the specified {@link JsonArray}
     *
     * @param array the json array to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static void writeImpl(JsonArray array, IJsonWriter writer) throws IOException, JsonException {
        writer.beginArray();

        for (JsonElement element : array) {
            writeImpl(element, writer);
        }

        writer.endArray();
    }

    /**
     * Writes the specified {@link JsonValue}
     *
     * @param value the json value to be written
     * @param writer the json based stream
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem
     */
    private static void writeImpl(JsonValue value, IJsonWriter writer) throws IOException, JsonException {
        if (value.isBoolean()) {
            writer.value(value.getAsBoolean());
        } else if (value.isNumber()) {
            writer.value(((JsonNumber) value).getNumber());
        } else if (value.isNull()) {
            writer.nullValue();
        } else {
            writer.value(value.getAsString());
        }
    }
}