package fr.poulpogaz.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface IJsonReader {

    void beginObject() throws IOException, JsonException;

    void endObject() throws IOException, JsonException;

    void beginArray() throws IOException, JsonException;

    void endArray() throws IOException, JsonException;

    String nextKey() throws IOException, JsonException;


    int nextInt() throws IOException, JsonException;

    float nextFloat() throws IOException, JsonException;

    long nextLong() throws IOException, JsonException;

    BigInteger nextBigInteger() throws IOException, JsonException;

    BigDecimal nextBigDecimal() throws IOException, JsonException;

    Number nextNumber() throws IOException, JsonException;


    String nextString() throws IOException, JsonException;

    void nextNull() throws IOException, JsonException;

    boolean nextBoolean() throws IOException, JsonException;

    JsonToken next() throws IOException, JsonException;




    boolean hasNextObject() throws IOException, JsonException;

    boolean isObjectEnd() throws IOException, JsonException;

    boolean hasNextArray() throws IOException, JsonException;

    boolean isArrayEnd() throws IOException, JsonException;

    boolean hasNextKey() throws IOException, JsonException;


    boolean hasNextInt() throws IOException, JsonException;

    boolean hasNextFloat() throws IOException, JsonException;

    boolean hasNextLong() throws IOException, JsonException;

    boolean hasNextBigInteger() throws IOException, JsonException;

    boolean hasNextBigDecimal() throws IOException, JsonException;

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