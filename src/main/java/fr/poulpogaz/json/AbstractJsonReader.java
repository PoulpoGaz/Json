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

/**
 * An intermediate class that implements most of the methods
 * of {@link IJsonReader}
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class AbstractJsonReader implements IJsonReader, AutoCloseable {

    /** The input reader **/
    protected Reader in;


    /** An object which verify the syntax and acts as a stack **/
    protected JsonReadContext context;

    /** The last token **/
    protected JsonToken previousToken = null;

    /** The current token **/
    protected JsonToken currentToken = null;

    /** The value of the current token if the token is a string based token**/
    protected String stringToken;

    /** The value of the current token if the token is a number token**/
    protected Number numberToken;

    /** The value of the current token if the token is a s boolean token**/
    protected boolean booleanToken;

    private boolean closed = false;

    /**
     * Creates a new instance that reads a json stream from an
     * {@link InputStream}
     *
     * @param in a json encoded input stream
     */
    public AbstractJsonReader(InputStream in) {
        this(new InputStreamReader(in, StandardCharsets.UTF_8));
    }

    /**
     * Creates a new instance that reads a json stream from an
     * {@link Reader}
     *
     * @param in a json encoded reader
     */
    public AbstractJsonReader(Reader in) {
        this.in = in;

        context = JsonReadContext.createRootContext();
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#BEGIN_OBJECT_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader beginObject() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.BEGIN_OBJECT_TOKEN) {
            throwException("BEGIN_OBJECT_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#END_OBJECT_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader endObject() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.END_OBJECT_TOKEN) {
            throwException("END_OBJECT_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader beginArray() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.BEGIN_ARRAY_TOKEN) {
            throwException("BEGIN_ARRAY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#END_ARRAY_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader endArray() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.END_ARRAY_TOKEN) {
            throwException("END_ARRAY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return this;
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#KEY_TOKEN}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public String nextKey() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.KEY_TOKEN) {
            throwException("KEY_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return stringToken;
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#INT_TOKEN}
     * and returns his value as {@code int}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public int nextInt() throws IOException, JsonException {
        peek();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.intValue();
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@code float}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public float nextFloat() throws IOException, JsonException {
        peek();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.floatValue();
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@code double}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public double nextDouble() throws IOException, JsonException {
        peek();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken.doubleValue();
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#LONG_TOKEN}
     * and returns his value as {@code long}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public long nextLong() throws IOException, JsonException {
        peek();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }


        consumeToken();

        return numberToken.longValue();
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#BIG_INTEGER_TOKEN}
     * and returns his value as {@link BigInteger}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public BigInteger nextBigInteger() throws IOException, JsonException {
        peek();

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

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@link BigDecimal}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public BigDecimal nextBigDecimal() throws IOException, JsonException {
        peek();

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

    /**
     * Consumes the next token, asserts that {@link JsonToken#isNumber()} returns {@code true}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public Number nextNumber() throws IOException, JsonException {
        peek();

        if (!currentToken.isNumber()) {
            throwException("INT_TOKEN or FLOAT_TOKEN or LONG_TOKEN or DECIMAL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return numberToken;
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#STRING_TOKEN}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public String nextString() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.STRING_TOKEN) {
            throwException("STRING_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return stringToken;
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#NULL_TOKEN}
     *
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public void nextNull() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.NULL_TOKEN) {
            throwException("NULL_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();
    }

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#BOOLEAN_TOKEN}
     * and returns his value as {@code boolean}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean nextBoolean() throws IOException, JsonException {
        peek();

        if (currentToken != JsonToken.BOOLEAN_TOKEN) {
            throwException("BOOLEAN_TOKEN expected, but was " + currentToken, true);
        }

        consumeToken();

        return booleanToken;
    }

    /**
     * Consumes the next token, asserts that it isn't {@link JsonToken#END_TOKEN}
     * and returns the token and his value, if it exists, in a {@link Pair>}
     *
     * @return the next token and his value, if it exists, in a {@link Pair}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public Pair<JsonToken, Object> next() throws IOException, JsonException {
        peek();

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

    /**
     * Peek the next token. The token value is stored in
     * {@link #currentToken}. His value can be stored
     * in {@link #numberToken}, {@link #stringToken} or
     * {@link #booleanToken}
     *
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    protected abstract void peek() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BEGIN_OBJECT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_OBJECT_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextObject() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.BEGIN_OBJECT_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#END_OBJECT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#END_OBJECT_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean isObjectEnd() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.END_OBJECT_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextArray() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.BEGIN_ARRAY_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#END_ARRAY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#END_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean isArrayEnd() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.END_ARRAY_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#KEY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#KEY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextKey() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.KEY_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#INT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#INT_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextInt() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.INT_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#LONG_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#LONG_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextLong() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.LONG_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BIG_INTEGER_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BIG_INTEGER_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextBigInteger() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.BIG_INTEGER_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#DECIMAL_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#DECIMAL_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextDecimalNumber() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.DECIMAL_TOKEN;
    }

    /**
     * Peeks the next token and returns {@link JsonToken#isNumber()}
     *
     * @return {@link JsonToken#isNumber()}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextNumber() throws IOException, JsonException {
        peek();

        return currentToken.isNumber();
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#STRING_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#STRING_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextString() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.STRING_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#NULL_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#NULL_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextNull() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.NULL_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BOOLEAN_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BOOLEAN_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNextBoolean() throws IOException, JsonException {
        peek();

        return currentToken == JsonToken.BOOLEAN_TOKEN;
    }

    /**
     * Peeks the next token and returns true if the next token is't {@link JsonToken#END_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public boolean hasNext() throws IOException, JsonException {
        peek();

        return currentToken != JsonToken.END_TOKEN;
    }

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#KEY_TOKEN},
     * by using the {@link #nextKey()} methods, but don't returns his value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader skipKey() throws IOException, JsonException {
        nextKey();

        return this;
    }

    /**
     * Skips the next value. If it is an array or an object,
     * it skips all nested elements
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader skipValue() throws IOException, JsonException {
        peek();

        if (currentToken == JsonToken.BEGIN_ARRAY_TOKEN) {
            consumeToken();

            while (!isArrayEnd()) {
                skipValue();
            }

            endArray();

        } else if (currentToken == JsonToken.BEGIN_OBJECT_TOKEN) {
            consumeToken();

            while (!isObjectEnd()) {
                skipField();
            }

            endObject();

        } else if (!currentToken.isValue()) {
            throwException("Value token expected, but was " + currentToken, true);
        } else {
            consumeToken();
        }

        return this;
    }

    /**
     * Skips the next key and next value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    @Override
    public IJsonReader skipField() throws IOException, JsonException {
        return skipKey().skipValue();
    }

    /**
     * Close the streams and releases resources
     *
     * @throws IOException If an I/O error occurs
     * @see java.io.Writer#close()
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

    /**
     * @return true if the stream is closed
     */
    protected boolean isClosed() {
        return closed;
    }

    /**
     * Consumes the next token: sets {@link #previousToken} to
     * {@link #currentToken} and sets {@link #currentToken} to
     * {@code null}
     */
    protected void consumeToken() {
        previousToken = currentToken;
        currentToken = null;
    }

    /**
     * Throws a {@link JsonException} with specified message
     *
     * @param message the message for the exception
     * @param showContext if true adds to the message the current json context
     * @throws JsonException throws a {@link JsonException} with specified message
     */
    protected void throwException(String message, boolean showContext) throws JsonException {
        if (showContext) {
            throw new JsonException(message + " (in " + context + ")");
        } else {
            throw new JsonException(message);
        }
    }
}