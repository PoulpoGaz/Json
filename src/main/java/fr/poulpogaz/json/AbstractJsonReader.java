package fr.poulpogaz.json;

import fr.poulpogaz.json.context.JsonReadContext;
import fr.poulpogaz.json.utils.Pair;

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
    public IJsonReader beginObject() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BEGIN_OBJECT_TOKEN) {
            throwException("BEGIN_OBJECT_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    @Override
    public IJsonReader endObject() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.END_OBJECT_TOKEN) {
            throwException("END_OBJECT_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    @Override
    public IJsonReader beginArray() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BEGIN_ARRAY_TOKEN) {
            throwException("BEGIN_ARRAY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    @Override
    public IJsonReader endArray() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.END_ARRAY_TOKEN) {
            throwException("END_ARRAY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    @Override
    public String nextKey() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.KEY_TOKEN) {
            throwException("KEY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return stringToken;
    }

    @Override
    public int nextInt() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.intValue();
    }

    @Override
    public float nextFloat() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.floatValue();
    }

    @Override
    public double nextDouble() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.doubleValue();
    }

    @Override
    public long nextLong() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }


        consumeToken();

        return numberToken.longValue();
    }

    @Override
    public BigInteger nextBigInteger() throws IOException, JsonException {
        nextToken();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
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
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
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
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken;
    }

    @Override
    public String nextString() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.STRING_TOKEN) {
            throwException("STRING_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return stringToken;
    }

    @Override
    public void nextNull() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.NULL_TOKEN) {
            throwException("NULL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();
    }

    @Override
    public boolean nextBoolean() throws IOException, JsonException {
        nextToken();

        if (currentToken != JsonToken.BOOLEAN_TOKEN) {
            throwException("BOOLEAN_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return booleanToken;
    }

    @Override
    public Pair<JsonToken, Object> next() throws IOException, JsonException {
        nextToken();

        if (currentToken == JsonToken.END_TOKEN) {
            throw new JsonException("EOF");
        }

        consumeToken();

        if (previousToken.isNumber()) {
            return new Pair<>(previousToken, numberToken);
        } else if (previousToken == JsonToken.BOOLEAN_TOKEN) {
            return new Pair<>(previousToken, booleanToken);
        } else if (previousToken == JsonToken.STRING_TOKEN) {
            return new Pair<>(previousToken, stringToken);
        } else {
            return new Pair<>(previousToken, null);
        }
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
    public boolean hasNextDecimalNumber() throws IOException, JsonException {
        nextToken();

        return currentToken == JsonToken.DECIMAL_TOKEN;
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

        } else if (currentToken == JsonToken.BEGIN_OBJECT_TOKEN) {
            consumeToken();

            while (!isObjectEnd()) {
                skipField();
            }

        } else if (!currentToken.isValue()) {
            throwException("Value token expected, but was " + currentToken, true);
        } else {
            consumeToken();
        }

        return this;
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

    protected void throwException(String message, boolean showContext) throws JsonException {
        if (showContext) {
            throw new JsonException(message + " ( in" + context + ")");
        } else {
            throw new JsonException(message);
        }
    }
}