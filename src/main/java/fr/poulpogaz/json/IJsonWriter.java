package fr.poulpogaz.json;

import java.io.IOException;

public interface IJsonWriter {

    IJsonWriter beginObject() throws IOException, JsonException;

    IJsonWriter endObject() throws IOException, JsonException;

    IJsonWriter beginArray() throws IOException, JsonException;

    IJsonWriter endArray() throws IOException, JsonException;


    IJsonWriter key(String key) throws IOException, JsonException;


    IJsonWriter nullValue() throws IOException, JsonException;

    IJsonWriter value(boolean value) throws IOException, JsonException;

    IJsonWriter value(int value) throws IOException, JsonException;

    IJsonWriter value(float value) throws IOException, JsonException;

    IJsonWriter value(Number value) throws IOException, JsonException;

    IJsonWriter value(String value) throws IOException, JsonException;

    
    IJsonWriter nullField(String key) throws IOException, JsonException;

    IJsonWriter field(String key, boolean value) throws IOException, JsonException;

    IJsonWriter field(String key, int value) throws IOException, JsonException;

    IJsonWriter field(String key, float value) throws IOException, JsonException;

    IJsonWriter field(String key, Number value) throws IOException, JsonException;

    IJsonWriter field(String key, String value) throws IOException, JsonException;


    void flush() throws IOException;

    void close() throws IOException, JsonException;
}