package fr.poulpogaz.json;

import java.io.IOException;

/**
 * A base class that defines methods for writing json files
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public interface IJsonWriter {

    /**
     * Begins writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter beginObject() throws IOException, JsonException;

    /**
     * Ends writing a new object
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter endObject() throws IOException, JsonException;

    /**
     * Begins writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter beginArray() throws IOException, JsonException;

    /**
     * Ends writing an array
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter endArray() throws IOException, JsonException;

    /**
     * Writes the specified key
     *
     * @param key the key to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter key(String key) throws IOException, JsonException;


    /**
     * Writes a {@code null} value
     *
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter nullValue() throws IOException, JsonException;

    /**
     * Writes the specified {@code boolean} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(boolean value) throws IOException, JsonException;

    /**
     * Writes the specified {@code byte} value
     *
     * @param value the byte to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(byte value) throws IOException, JsonException;

    /**
     * Writes the specified {@code short} value
     *
     * @param value the short to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(short value) throws IOException, JsonException;

    /**
     * Writes the specified {@code int} value
     *
     * @param value the int to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(int value) throws IOException, JsonException;

    /**
     * Writes the specified {@code long} value
     *
     * @param value the long to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(long value) throws IOException, JsonException;

    /**
     * Writes the specified {@code float} value
     *
     * @param value the float to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(float value) throws IOException, JsonException;

    /**
     * Writes the specified {@code double} value
     *
     * @param value the int to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(double value) throws IOException, JsonException;

    /**
     * Writes the specified {@link Number} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(Number value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} value
     *
     * @param value the boolean to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter value(String value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} key and
     * a {@code null} value
     *
     * @param key the key to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter nullField(String key) throws IOException, JsonException;

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
    IJsonWriter field(String key, boolean value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} key and
     * the specified {@code byte} value
     *
     * @param key the key to write
     * @param value the byte to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter field(String key, byte value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} key and
     * the specified {@code short} value
     *
     * @param key the key to write
     * @param value the byte to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter field(String key, short value) throws IOException, JsonException;


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
    IJsonWriter field(String key, int value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} key and
     * the specified {@code long} value
     *
     * @param key the key to write
     * @param value the long to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter field(String key, long value) throws IOException, JsonException;

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
    IJsonWriter field(String key, float value) throws IOException, JsonException;

    /**
     * Writes the specified {@link String} key and
     * the specified {@code double} value
     *
     * @param key the key to write
     * @param value the double to write
     * @return itself
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem
     */
    IJsonWriter field(String key, double value) throws IOException, JsonException;

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
    IJsonWriter field(String key, Number value) throws IOException, JsonException;

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
    IJsonWriter field(String key, String value) throws IOException, JsonException;

    /**
     * Flushes the stream.
     *
     * @throws IOException If an I/O error occurs
     * @see java.io.Writer#flush()
     */
    void flush() throws IOException;

    /**
     * Close the streams and releases resources
     *
     * @throws IOException If an I/O error occurs
     * @throws JsonException If the written json cannot be closed
     * @see java.io.Writer#close()
     */
    void close() throws IOException, JsonException;
}