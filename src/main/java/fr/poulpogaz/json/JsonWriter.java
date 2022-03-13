package fr.poulpogaz.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * A json writer. It doesn't write json with
 * indentation, spaces, etc.
 *
 * @author PoulpoGaz
 * @version 1.0
 * @see JsonPrettyWriter
 */
public class JsonWriter extends AbstractJsonWriter {

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param os the writer
     */
    public JsonWriter(OutputStream os) {
        super(os);
    }

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param out the writer
     */
    public JsonWriter(Writer out) {
        super(out);
    }

    /**
     * Begins writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter beginObject() throws IOException, JsonException {
        writeCommaIfNeeded();
        scope = scope.createObjectScope();
        out.write('{');

        return this;
    }

    /**
     * Ends writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter endObject() throws IOException, JsonException {
        scope = scope.close();
        out.write('}');

        return this;
    }

    /**
     * Begins writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter beginArray() throws IOException, JsonException {
        writeCommaIfNeeded();
        scope = scope.createArrayScope();
        out.write('[');

        return this;
    }

    /**
     * Ends writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter endArray() throws IOException, JsonException {
        scope = scope.close();
        out.write(']');

        return this;
    }

    /**
     * Writes the specified key
     *
     * @param key the key to writer
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    @Override
    public IJsonWriter key(String key) throws IOException, JsonException {
        writeCommaIfNeeded();
        scope.newKey();
        writeString(key);
        out.write(":");

        return this;
    }

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
    protected IJsonWriter value(String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        scope.newValue();

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }

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
    protected IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        scope.newField();
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