package fr.poulpogaz.json;

import fr.poulpogaz.json.scope.JsonWriteScope;
import fr.poulpogaz.json.scope.RootWriteScope;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * An intermediate class that implements most of the methods
 * of {@link IJsonWriter}
 *
 * @author PoulpoGaz
 * @version 1.0.1
 */
public abstract class AbstractJsonWriter implements IJsonWriter {

    /** the writer**/
    protected final Writer out;

    /** An object which verify the syntax and acts as a stack **/
    protected JsonWriteScope scope;

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param os the writer
     */
    public AbstractJsonWriter(OutputStream os) {
        this(new OutputStreamWriter(os, StandardCharsets.UTF_8));
    }

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param out the writer
     */
    public AbstractJsonWriter(Writer out) {
        this.out = out;

        scope = new RootWriteScope();
    }

    /**
     * Begins writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public abstract IJsonWriter beginObject() throws IOException, JsonException;

    /**
     * Ends writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public abstract IJsonWriter endObject() throws IOException, JsonException;

    /**
     * Begins writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public abstract IJsonWriter beginArray() throws IOException, JsonException;

    /**
     * Ends writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public abstract IJsonWriter endArray() throws IOException, JsonException;

    /**
     * Writes the specified key
     *
     * @param key the key to writer
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public abstract IJsonWriter key(String key) throws IOException, JsonException;

    /**
     * Writes the specified {@code value} and wraps
     * it between quote if needed
     *
     * @param value the value to write
     * @param wrap true if the value needs to be wrapped
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    protected abstract IJsonWriter value(String value, boolean wrap) throws IOException, JsonException;

    /**
     * Writes the specified {@code key} and the specified
     * {@code value} and wrap it between quote if needed
     *
     * @param key the key to write
     * @param value the value to write
     * @param wrap true if the value needs to be wrapped
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    protected abstract IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException;

    /**
     * Writes a {@code null} value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter nullValue() throws IOException, JsonException {
        return value("null", false);
    }

    /**
     * Writes the specified {@code boolean} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter value(boolean value) throws IOException, JsonException {
        return value(Boolean.toString(value), false);
    }

    /**
     * Writes the specified {@code int} value
     *
     * @param value the int to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter value(int value) throws IOException, JsonException {
        return value(Integer.toString(value), false);
    }

    /**
     * Writes the specified {@code float} value
     *
     * @param value the float to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter value(float value) throws IOException, JsonException {
        return value(Float.toString(value), false);
    }

    /**
     * Writes the specified {@link Number} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter value(Number value) throws IOException, JsonException {
        return value(value.toString(), false);
    }

    /**
     * Writes the specified {@link String} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter value(String value) throws IOException, JsonException {
        return value(value, true);
    }

    /**
     * Writes the specified {@link String} key and
     * a {@code null} value
     *
     * @param key the key to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter nullField(String key) throws IOException, JsonException {
        return field(key, "null", false);
    }

    /**
     * Writes the specified {@link String} key and
     * the specified {@code boolean} value
     *
     * @param key the key to write
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter field(String key, boolean value) throws IOException, JsonException {
        return field(key, Boolean.toString(value), false);
    }

    /**
     * Writes the specified {@link String} key and
     * the specified {@code int} value
     *
     * @param key the key to write
     * @param value the int to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter field(String key, int value) throws IOException, JsonException {
        return field(key, Integer.toString(value), false);
    }

    /**
     * Writes the specified {@link String} key and
     * the specified {@code float} value
     *
     * @param key the key to write
     * @param value the float to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter field(String key, float value) throws IOException, JsonException {
        return field(key, Float.toString(value), false);
    }

    /**
     * Writes the specified {@link String} key and
     * the specified {@link Number} value
     *
     * @param key the key to write
     * @param value the number to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter field(String key, Number value) throws IOException, JsonException {
        return field(key, value.toString(), false);
    }

    /**
     * Writes the specified {@link String} key and
     * the specified {@link String} value
     *
     * @param key the key to write
     * @param value the string to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter field(String key, String value) throws IOException, JsonException {
        return field(key, value, true);
    }

    /**
     * Utility method for writing strings between quote
     *
     * @param str the string to write
     * @throws IOException If an I/O error occurs
     */
    protected void writeString(String str) throws IOException{
        StringBuilder builder = new StringBuilder(str.length() + 2);
        builder.append('"');

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            switch (c) {
                case '"' -> builder.append("\\\"");
                case '\\' -> builder.append("\\\\");
                case '\n' -> builder.append("\\n");
                case '\t' -> builder.append("\\t");
                case '\r' -> builder.append("\\r");
                case '\b' -> builder.append("\\b");
                case '\f' -> builder.append("\\f");
                default -> builder.append(c);
            }
        }

        builder.append('"');

        out.write(builder.toString());
    }

    /**
     * Utility method for writing a comma if needed
     *
     * @return true if the comma has been written else false
     * @throws IOException If an I/O error occurs
     */
    protected boolean writeCommaIfNeeded() throws IOException {
        if (scope.needComma()) {
            out.write(",");

            return true;
        } else {
            return false;
        }
    }

    /**
     * Flushes the stream.
     *
     * @throws IOException If an I/O error occurs
     * @see Writer#flush()
     */
    @Override
    public void flush() throws IOException {
        out.flush();
    }

    /**
     * Close the streams and releases resources
     *
     * @throws IOException If an I/O error occurs
     * @throws JsonException If the written json cannot be closed
     * @see Writer#close()
     */
    @Override
    public void close() throws IOException, JsonException {
        if (scope.close() != null) {
            throw new JsonException("Json is not terminated");
        }

        out.close();
    }
}