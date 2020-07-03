package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.IJsonWriter;
import fr.poulpogaz.json.JsonException;
import fr.poulpogaz.json.JsonPrettyWriter;
import fr.poulpogaz.json.tree.value.JsonNumber;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

public class JsonTreeWriter {

    public static void write(JsonElement element, Writer writer) throws IOException, JsonException {
        if (element.isObject()) {
            write((JsonObject) element, new JsonPrettyWriter(writer));
        } else if (element.isArray()) {
            write((JsonArray) element, new JsonPrettyWriter(writer));
        } else if (element.isValue()) {
            write((JsonValue) element, new JsonPrettyWriter(writer));
        }
    }

    public static void write(JsonObject object, Writer writer) throws IOException, JsonException {
        write(object, new JsonPrettyWriter(writer));
    }

    public static void write(JsonArray array, Writer writer) throws IOException, JsonException {
        write(array, new JsonPrettyWriter(writer));
    }

    public static void write(JsonValue value, Writer writer) throws IOException, JsonException {
        write(value, new JsonPrettyWriter(writer));
    }


    public static void write(JsonElement element, OutputStream os) throws IOException, JsonException {
        if (element.isObject()) {
            write((JsonObject) element, new JsonPrettyWriter(os));
        } else if (element.isArray()) {
            write((JsonArray) element, new JsonPrettyWriter(os));
        } else if (element.isValue()) {
            write((JsonValue) element, new JsonPrettyWriter(os));
        }
    }

    public static void write(JsonObject object, OutputStream os) throws IOException, JsonException {
        write(object, new JsonPrettyWriter(os));
    }

    public static void write(JsonArray array, OutputStream os) throws IOException, JsonException {
        write(array, new JsonPrettyWriter(os));
    }

    public static void write(JsonValue value, OutputStream os) throws IOException, JsonException {
        write(value, new JsonPrettyWriter(os));
    }


    public static void write(JsonElement element, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(element, writer);
        writer.close();
    }

    public static void write(JsonObject object, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(object, writer);
        writer.close();
    }

    public static void write(JsonArray array, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(array, writer);
        writer.close();
    }

    public static void write(JsonValue value, IJsonWriter writer) throws IOException, JsonException {
        writeImpl(value, writer);
        writer.close();
    }


    private static void writeImpl(JsonElement element, IJsonWriter writer) throws IOException, JsonException {
        if (element.isObject()) {
            writeImpl((JsonObject) element, writer);
        } else if (element.isArray()) {
            writeImpl((JsonArray) element, writer);
        } else if (element.isValue()) {
            writeImpl((JsonValue) element, writer);
        }
    }

    private static void writeImpl(JsonObject object, IJsonWriter writer) throws IOException, JsonException {
        writer.beginObject();

        for (Map.Entry<String, JsonElement> element : object.entrySet()) {
            writer.key(element.getKey());
            writeImpl(element.getValue(), writer);
        }

        writer.endObject();
    }

    private static void writeImpl(JsonArray array, IJsonWriter writer) throws IOException, JsonException {
        writer.beginArray();

        for (JsonElement element : array) {
            writeImpl(element, writer);
        }

        writer.endArray();
    }

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