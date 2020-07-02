package fr.poulpogaz.json;

import fr.poulpogaz.json.utils.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface IJsonReader {

    IJsonReader beginObject() throws IOException, JsonException;

    IJsonReader endObject() throws IOException, JsonException;

    IJsonReader beginArray() throws IOException, JsonException;

    IJsonReader endArray() throws IOException, JsonException;

    String nextKey() throws IOException, JsonException;


    int nextInt() throws IOException, JsonException;

    float nextFloat() throws IOException, JsonException;

    double nextDouble() throws IOException, JsonException;

    long nextLong() throws IOException, JsonException;

    BigInteger nextBigInteger() throws IOException, JsonException;

    BigDecimal nextBigDecimal() throws IOException, JsonException;

    Number nextNumber() throws IOException, JsonException;


    String nextString() throws IOException, JsonException;

    void nextNull() throws IOException, JsonException;

    boolean nextBoolean() throws IOException, JsonException;

    Pair<JsonToken, Object> next() throws IOException, JsonException;




    boolean hasNextObject() throws IOException, JsonException;

    boolean isObjectEnd() throws IOException, JsonException;

    boolean hasNextArray() throws IOException, JsonException;

    boolean isArrayEnd() throws IOException, JsonException;

    boolean hasNextKey() throws IOException, JsonException;


    boolean hasNextInt() throws IOException, JsonException;

    boolean hasNextLong() throws IOException, JsonException;

    boolean hasNextBigInteger() throws IOException, JsonException;

    boolean hasNextDecimalNumber() throws IOException, JsonException;

    boolean hasNextNumber() throws IOException, JsonException;


    boolean hasNextString() throws IOException, JsonException;

    boolean hasNextNull() throws IOException, JsonException;

    boolean hasNextBoolean() throws IOException, JsonException;

    boolean hasNext() throws IOException, JsonException;

    IJsonReader skipKey() throws IOException, JsonException;

    IJsonReader skipValue() throws IOException, JsonException;

    IJsonReader skipField() throws IOException, JsonException;

    void close() throws IOException;
}