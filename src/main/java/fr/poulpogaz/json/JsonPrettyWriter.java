package fr.poulpogaz.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * A json writer. It writes json with
 * indentation, spaces, etc.
 *
 * @author PoulpoGaz
 * @version 1.0
 * @see JsonWriter
 */
public class JsonPrettyWriter extends AbstractJsonWriter {

    private int depth = 0;

    /**
     * The size of the indentation.
     * If zero, no spaces, indentation are written.
     */
    private int indent = 4;

    /** True if you want to write the indentation with tabs and not spaces **/
    private boolean useTabs;

    /** Uses {@code "\r\n"} line separator if true else {@code '\n'} **/
    private boolean useWindowsLineSeparator;

    /** cache value **/
    private String space;

    /** cache value **/
    private String lineSeparator;

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param os the writer
     */
    public JsonPrettyWriter(OutputStream os) {
        super(os);
    }

    /**
     * Creates a new instance that writes json to a
     * {@link Writer}
     *
     * @param out the writer
     */
    public JsonPrettyWriter(Writer out) {
        super(out);

        setUseTabs(false);
        setUseWindowsLineSeparator(true);
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
        boolean comma = writeCommaIfNeeded();
        boolean wasArray = scope.isArray();
        scope = scope.createObjectScope();

        if (comma || wasArray) {
            newLine();
        }

        out.write('{');
        depth++;

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
        depth--;
        newLine();
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
        boolean comma = writeCommaIfNeeded();
        boolean wasArray = scope.isArray();
        scope = scope.createArrayScope();

        if (comma || wasArray) {
            newLine();
        }

        out.write("[");
        depth++;

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
        depth--;
        newLine();
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
        newLine();
        writeString(key);
        out.write(": ");

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
    @Override
    protected IJsonWriter value(String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        scope.newValue();

        if (scope.isArray()) {
            newLine();
        }

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
    @Override
    protected IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        newLine();
        scope.newField();
        writeString(key);
        out.write(": ");

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }

    /**
     * Begins a new line and writes the indentation
     *
     * @throws IOException If an I/O error occurs
     */
    protected void newLine() throws IOException {
        out.write(lineSeparator);

        if (indent > 0) {
            for (int i = 0; i < depth; i++) {
                out.write(space);
            }
        }
    }

    /**
     * Writes the field separator: {@code ": "}
     * or {@code ':'} depending on the size of the indentation
     *
     * @throws IOException If an I/O error occurs
     */
    protected void writeFieldSeparator() throws IOException {
        if (indent > 0) {
            out.write(": ");
        } else {
            out.write(':');
        }
    }

    /**
     * @return the size of the indentation
     */
    public int getIndent() {
        return indent;
    }

    /**
     * @param indent set the size of the indentation
     */
    public void setIndent(int indent) {
        this.indent = Math.max(0, indent);

        if (useTabs) {
            space = "\t".repeat(indent);
        } else {
            space = " ".repeat(indent);
        }
    }

    /**
     * @return {@code useTabs}: {@code true} if the writer
     *          writes tabs when indenting
     */
    public boolean isUseTabs() {
        return useTabs;
    }

    /**
     * @param useTabs set the {@code useTabs} value,
     *                {@code true} if you want to write tabs
     *                when indenting
     */
    public void setUseTabs(boolean useTabs) {
        this.useTabs = useTabs;

        if (useTabs) {
            space = "\t".repeat(indent);
        } else {
            space = " ".repeat(indent);
        }
    }

    /**
     * @return {@code true} if the writer use windows line separator ({@code "\r\n})
     */
    public boolean isUsingWindowsLineSeparator() {
        return useWindowsLineSeparator;
    }

    /**
     * @param useWindowsLineSeparator set the {@code useWindowsLineSeparator} value,
     *                                {@code true} if you want to write {@code "\r\n"}
     *                                at the end of a line, else {@code false} if you w
     *                                ant to write {@code '\n'} at the end of a line
     */
    public void setUseWindowsLineSeparator(boolean useWindowsLineSeparator) {
        this.useWindowsLineSeparator = useWindowsLineSeparator;

        if (useWindowsLineSeparator) {
            lineSeparator = "\r\n";
        } else {
            lineSeparator = "\n";
        }
    }
}