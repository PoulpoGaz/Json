package fr.poulpogaz.json;

import fr.poulpogaz.json.utils.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A base class that defines methods for reading json files
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public interface IJsonReader {

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#BEGIN_OBJECT_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader beginObject() throws IOException, JsonException;

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#END_OBJECT_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader endObject() throws IOException, JsonException;

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader beginArray() throws IOException, JsonException;

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#END_ARRAY_TOKEN}
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader endArray() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#KEY_TOKEN}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    String nextKey() throws IOException, JsonException;


    /**
     * Consumes the next token, asserts that it is {@link JsonToken#INT_TOKEN}
     * and returns his value as {@code int}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    int nextInt() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@code float}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    float nextFloat() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@code double}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    double nextDouble() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#LONG_TOKEN}
     * and returns his value as {@code long}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    long nextLong() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#BIG_INTEGER_TOKEN}
     * and returns his value as {@link BigInteger}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    BigInteger nextBigInteger() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#DECIMAL_TOKEN}
     * and returns his value as {@link BigDecimal}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    BigDecimal nextBigDecimal() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that {@link JsonToken#isNumber()} returns {@code true}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    Number nextNumber() throws IOException, JsonException;


    /**
     * Consumes the next token, asserts that it is {@link JsonToken#STRING_TOKEN}
     * and returns his value as {@link String}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    String nextString() throws IOException, JsonException;

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#NULL_TOKEN}
     *
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    void nextNull() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it is {@link JsonToken#BOOLEAN_TOKEN}
     * and returns his value as {@code boolean}
     *
     * @return the token value
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean nextBoolean() throws IOException, JsonException;

    /**
     * Consumes the next token, asserts that it isn't {@link JsonToken#END_TOKEN}
     * and returns the token and his value, if it exists, in a {@link Pair>}
     *
     * @return the next token and his value, if it exists, in a {@link Pair}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    Pair<JsonToken, Object> next() throws IOException, JsonException;


    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BEGIN_OBJECT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_OBJECT_TOKEN}
*              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextObject() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#END_OBJECT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#END_OBJECT_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean isObjectEnd() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextArray() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#END_ARRAY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#END_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean isArrayEnd() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#KEY_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#KEY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextKey() throws IOException, JsonException;


    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#INT_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#INT_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextInt() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#LONG_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#LONG_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextLong() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BIG_INTEGER_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BIG_INTEGER_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextBigInteger() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#DECIMAL_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#DECIMAL_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextDecimalNumber() throws IOException, JsonException;

    /**
     * Peeks the next token and returns {@link JsonToken#isNumber()}
     *
     * @return {@link JsonToken#isNumber()}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextNumber() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#STRING_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#STRING_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextString() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#NULL_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#NULL_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextNull() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is
     * {@link JsonToken#BOOLEAN_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BOOLEAN_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNextBoolean() throws IOException, JsonException;

    /**
     * Peeks the next token and returns true if the next token is't {@link JsonToken#END_TOKEN}
     *
     * @return {@code true} if the next token is {@link JsonToken#BEGIN_ARRAY_TOKEN}
     *              else {@code false}
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    boolean hasNext() throws IOException, JsonException;

    /**
     * Consumes the next token and asserts that it is {@link JsonToken#KEY_TOKEN},
     * by using the {@link #nextKey()} methods, but don't returns his value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader skipKey() throws IOException, JsonException;

    /**
     * Skips the next value. If it is an array or an object,
     * it skips all nested elements
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader skipValue() throws IOException, JsonException;

    /**
     * Skips the next key and next value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    IJsonReader skipField() throws IOException, JsonException;

    /**
     * Close the streams and releases resources
     *
     * @throws IOException If an I/O error occurs
     */
    void close() throws IOException;
}