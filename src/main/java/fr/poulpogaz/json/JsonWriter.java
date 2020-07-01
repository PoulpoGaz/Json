package fr.poulpogaz.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class JsonWriter extends AbstractJsonWriter {

    public JsonWriter(OutputStream os) {
        super(os);
    }

    public JsonWriter(Writer out) {
        super(out);
    }

    @Override
    public IJsonWriter beginObject() throws IOException, JsonException {
        writeCommaIfNeeded();
        context = context.createObjectContext();
        out.write('{');

        return this;
    }

    @Override
    public IJsonWriter endObject() throws IOException, JsonException {
        context = context.close();
        out.write('}');

        return this;
    }

    @Override
    public IJsonWriter beginArray() throws IOException, JsonException {
        writeCommaIfNeeded();
        context = context.createArrayContext();
        out.write('[');

        return this;
    }

    @Override
    public IJsonWriter endArray() throws IOException, JsonException {
        context = context.close();
        out.write(']');

        return this;
    }

    @Override
    public IJsonWriter key(String key) throws IOException, JsonException {
        writeCommaIfNeeded();
        context.newKey();
        writeString(key);
        out.write(":");

        return this;
    }

    protected IJsonWriter value(String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        context.newValue();

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }

    protected IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        context.newField();
        writeString(key);
        out.write(":");

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }
}