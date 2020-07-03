package fr.poulpogaz.json;

import fr.poulpogaz.json.context.JsonWriteContext;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class AbstractJsonWriter implements IJsonWriter {

    protected final Writer out;
    protected JsonWriteContext context;

    public AbstractJsonWriter(OutputStream os) {
        this(new OutputStreamWriter(os, StandardCharsets.UTF_8));
    }

    public AbstractJsonWriter(Writer out) {
        this.out = out;

        context = JsonWriteContext.createRootContext();
    }

    @Override
    public abstract IJsonWriter beginObject() throws IOException, JsonException;

    @Override
    public abstract IJsonWriter endObject() throws IOException, JsonException;

    @Override
    public abstract IJsonWriter beginArray() throws IOException, JsonException;

    @Override
    public abstract IJsonWriter endArray() throws IOException, JsonException;

    @Override
    public abstract IJsonWriter key(String key) throws IOException, JsonException;

    protected abstract IJsonWriter value(String value, boolean wrap) throws IOException, JsonException;

    protected abstract IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException;

    @Override
    public IJsonWriter nullValue() throws IOException, JsonException {
        return value("null", false);
    }

    @Override
    public IJsonWriter value(boolean value) throws IOException, JsonException {
        return value(Boolean.toString(value), false);
    }

    @Override
    public IJsonWriter value(int value) throws IOException, JsonException {
        return value(Integer.toString(value), false);
    }

    @Override
    public IJsonWriter value(float value) throws IOException, JsonException {
        return value(Float.toString(value), false);
    }

    @Override
    public IJsonWriter value(Number value) throws IOException, JsonException {
        return value(value.toString(), false);
    }

    @Override
    public IJsonWriter value(String value) throws IOException, JsonException {
        return value(value, true);
    }

    @Override
    public IJsonWriter nullField(String key) throws IOException, JsonException {
        return field(key, "null", false);
    }

    @Override
    public IJsonWriter field(String key, boolean value) throws IOException, JsonException {
        return field(key, Boolean.toString(value), false);
    }

    @Override
    public IJsonWriter field(String key, int value) throws IOException, JsonException {
        return field(key, Integer.toString(value), false);
    }

    @Override
    public IJsonWriter field(String key, float value) throws IOException, JsonException {
        return field(key, Float.toString(value), false);
    }

    @Override
    public IJsonWriter field(String key, Number value) throws IOException, JsonException {
        return field(key, value.toString(), false);
    }

    @Override
    public IJsonWriter field(String key, String value) throws IOException, JsonException {
        return field(key, value, true);
    }

    protected void writeString(String str) throws IOException{
        StringBuilder builder = new StringBuilder(str.length() + 2);
        builder.append('"');

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case '"':
                    builder.append("\\\"");
                    break;
                case '\\':
                    builder.append("\\\\");
                case '\n':
                    builder.append("\\n");
                    break;
                case '\t':
                    builder.append("\\t");
                    break;
                case '\r':
                    builder.append("\\r");
                    break;
                case '\b':
                    builder.append("\\b");
                    break;
                case '\f':
                    builder.append("\\f");
                default:
                    builder.append(c);
                    break;
            }
        }

        builder.append('"');

        out.write(builder.toString());
    }

    protected boolean writeCommaIfNeeded() throws IOException {
        if (context.needComma()) {
            out.write(",");

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException, JsonException {
        if (context.close() != null) {
            throw new JsonException("Json is not terminated");
        }

        out.close();
    }
}