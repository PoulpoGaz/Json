package fr.poulpogaz.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class JsonPrettyWriter extends AbstractJsonWriter {

    private int depth = 0;

    private int indent = 4;
    private boolean useTabs;
    private boolean useWindowsLineSeparator;

    private String space;
    private String lineSeparator;

    public JsonPrettyWriter(OutputStream os) {
        super(os);
    }

    public JsonPrettyWriter(Writer out) {
        super(out);

        setUseTabs(false);
        setUseWindowsLineSeparator(true);
    }

    @Override
    public IJsonWriter beginObject() throws IOException, JsonException {
        boolean comma = writeCommaIfNeeded();
        boolean wasArray = context.isArray();
        context = context.createObjectContext();

        if (comma || wasArray) {
            newLine();
        }

        out.write('{');
        depth++;

        return this;
    }

    @Override
    public IJsonWriter endObject() throws IOException, JsonException {
        context = context.close();
        depth--;
        newLine();
        out.write('}');

        return this;
    }

    @Override
    public IJsonWriter beginArray() throws IOException, JsonException {
        boolean comma = writeCommaIfNeeded();
        boolean wasArray = context.isArray();
        context = context.createArrayContext();

        if (comma || wasArray) {
            newLine();
        }

        out.write("[");
        depth++;

        return this;
    }

    @Override
    public IJsonWriter endArray() throws IOException, JsonException {
        context = context.close();
        depth--;
        newLine();
        out.write(']');

        return this;
    }

    @Override
    public IJsonWriter key(String key) throws IOException, JsonException {
        writeCommaIfNeeded();
        context.newKey();
        newLine();
        writeString(key);
        out.write(": ");

        return this;
    }

    @Override
    protected IJsonWriter value(String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        context.newValue();

        if (context.isArray()) {
            newLine();
        }

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }

    @Override
    protected IJsonWriter field(String key, String value, boolean wrap) throws IOException, JsonException {
        writeCommaIfNeeded();
        newLine();
        context.newField();
        writeString(key);
        out.write(": ");

        if (wrap) {
            writeString(value);
        } else {
            out.write(value);
        }

        return this;
    }

    protected void newLine() throws IOException {
        out.write(lineSeparator);

        if (indent > 0) {
            for (int i = 0; i < depth; i++) {
                out.write(space);
            }
        }
    }

    protected void writeFieldSeparator() throws IOException {
        if (indent > 0) {
            out.write(": ");
        } else {
            out.write(':');
        }
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;

        if (useTabs) {
            space = "\t".repeat(indent);
        } else {
            space = " ".repeat(indent);
        }
    }

    public boolean isUseTabs() {
        return useTabs;
    }

    public void setUseTabs(boolean useTabs) {
        this.useTabs = useTabs;

        if (useTabs) {
            space = "\t".repeat(indent);
        } else {
            space = " ".repeat(indent);
        }
    }

    public boolean isUsingWindowsLineSeparator() {
        return useWindowsLineSeparator;
    }

    public void setUseWindowsLineSeparator(boolean useWindowsLineSeparator) {
        this.useWindowsLineSeparator = useWindowsLineSeparator;

        if (useWindowsLineSeparator) {
            lineSeparator = "\r\n";
        } else {
            lineSeparator = "\n";
        }
    }
}