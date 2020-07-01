package fr.poulpogaz.json;

import fr.poulpogaz.json.context.JsonReadContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public abstract class AbstractJsonReader implements IJsonReader, AutoCloseable {

    protected Reader in;

    protected JsonReadContext context;
    protected JsonToken previousToken = null;
    protected JsonToken currentToken = null;

    protected String stringToken;
    protected Number numberToken;
    protected boolean booleanToken;

    private boolean closed = false;

    public AbstractJsonReader(InputStream in) {
        this(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    public AbstractJsonReader(Reader in) {
        this.in = in;

        context = JsonReadContext.createRootContext();
    }

    @Override
    public void beginObject() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BEGIN_OBJECT_TOKEN) {
            throw new JsonException("BEGIN_OBJECT_TOKEN expected, but was " + currentToken);
        }

        consumeToken();
    }

    @Override
    public void endObject() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.END_OBJECT_TOKEN) {
            throw new JsonException("END_OBJECT_TOKEN expected, but was " + currentToken);
        }

        consumeToken();
    }

    @Override
    public void beginArray() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BEGIN_ARRAY_TOKEN) {
            throw new JsonException("BEGIN_ARRAY_TOKEN expected, but was " + currentToken);
        }

        consumeToken();
    }

    @Override
    public void endArray() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.END_ARRAY_TOKEN) {
            throw new JsonException("END_ARRAY_TOKEN expected, but was " + currentToken);
        }

        consumeToken();
    }

    @Override
    public String nextKey() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.KEY_TOKEN) {
            throw new JsonException("KEY_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return stringToken;
    }

    @Override
    public int nextInt() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return numberToken.intValue();
    }

    @Override
    public float nextFloat() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return numberToken.floatValue();
    }

    @Override
    public long nextLong() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }


        consumeToken();

        return numberToken.longValue();
    }

    @Override
    public BigInteger nextBigInteger() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        if (numberToken instanceof BigInteger) {
            return (BigInteger) numberToken;
        } else if (numberToken instanceof BigDecimal) {
            BigDecimal bigDecimal = (BigDecimal) numberToken;

            return bigDecimal.toBigInteger();
        } else {
            return new BigInteger(numberToken.toString());
        }
    }

    @Override
    public BigDecimal nextBigDecimal() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        if (numberToken instanceof BigDecimal) {
            return (BigDecimal) numberToken;
        } else {
            return new BigDecimal(numberToken.toString());
        }
    }

    @Override
    public Number nextNumber() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throw new JsonException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or BIG_INTEGER_TOKEN or BIG_DECIMAL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return numberToken;
    }

    @Override
    public String nextString() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.STRING_TOKEN) {
            throw new JsonException("STRING_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return stringToken;
    }

    @Override
    public void nextNull() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.NULL_TOKEN) {
            throw new JsonException("NULL_TOKEN expected, but was " + currentToken);
        }

        consumeToken();
    }

    @Override
    public boolean nextBoolean() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BOOLEAN_TOKEN) {
            throw new JsonException("BOOLEAN_TOKEN expected, but was " + currentToken);
        }

        consumeToken();

        return booleanToken;
    }

    @Override
    public JsonToken next() throws IOException, JsonException {
        nextToken();

        if (currentToken == JsonToken.END_TOKEN) {
            throw new JsonException("EOF");
        }

        consumeToken();

        return previousToken;
    }

    protected abstract void nextToken() throws IOException, JsonException;

    @Override
    public boolean hasNextObject() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.BEGIN_OBJECT_TOKEN;
    }

    @Override
    public boolean isObjectEnd() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.END_OBJECT_TOKEN;
    }

    @Override
    public boolean hasNextArray() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.BEGIN_ARRAY_TOKEN;
    }

    @Override
    public boolean isArrayEnd() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.END_ARRAY_TOKEN;
    }

    @Override
    public boolean hasNextKey() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.KEY_TOKEN;
    }

    @Override
    public boolean hasNextInt() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.INT_TOKEN;
    }

    @Override
    public boolean hasNextFloat() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.FLOAT_TOKEN;
    }

    @Override
    public boolean hasNextLong() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.LONG_TOKEN;
    }

    @Override
    public boolean hasNextBigInteger() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.BIG_INTEGER_TOKEN;
    }

    @Override
    public boolean hasNextBigDecimal() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.BIG_DECIMAL_TOKEN;
    }

    @Override
    public boolean hasNextNumber() throws IOException, JsonException {
        nextToken();

        return currentToken.isNumber();
    }

    @Override
    public boolean hasNextString() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.STRING_TOKEN;
    }

    @Override
    public boolean hasNextNull() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.NULL_TOKEN;
    }

    @Override
    public boolean hasNextBoolean() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.BOOLEAN_TOKEN;
    }

    @Override
    public boolean hasNext() throws IOException, JsonException {
        nextToken();

        return currentToken != JsonToken.END_TOKEN;
    }

    @Override
    public IJsonReader skipKey() throws IOException, JsonException {
        nextKey();

        return this;
    }

    @Override
    public IJsonReader skipValue() throws IOException, JsonException {
        nextToken();

        if (currentToken == JsonToken.BEGIN_ARRAY_TOKEN) {
            consumeToken();

            while (!isArrayEnd()) {
                skipValue();
            }

            return this;
        } else if (currentToken == JsonToken.BEGIN_OBJECT_TOKEN) {
            consumeToken();

            while (!isObjectEnd()) {
                skipField();
            }

            return this;
        } else if (!currentToken.isValue()) {
            throw new JsonException("Value token expected, but was " + currentToken);
        } else {
            consumeToken();

            return this;
        }
    }

    @Override
    public IJsonReader skipField() throws IOException, JsonException {
        return skipKey().skipValue();
    }

    /**
     * Clear all field and mark closes
     */
    @Override
    public void close() throws IOException {
        closed = true;

        currentToken = null;
        previousToken = null;
        context = null;

        stringToken = null;
        numberToken = null;

        in.close();

        in = null;

    }

    protected boolean isClosed() {
        return closed;
    }

    protected void consumeToken() {
        previousToken = currentToken;
        currentToken = null;
    }
}