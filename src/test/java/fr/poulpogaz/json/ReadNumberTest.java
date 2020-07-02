package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadNumberTest {

    private InputStream getResource() {
        return JsonReadTest.class.getResourceAsStream("/number_test.json");
    }

    @Test
    void numberTest() throws IOException, JsonException {
        IJsonReader reader = new JsonReader(getResource());

        reader.beginObject();

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(50, reader.nextInt());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(550, reader.nextInt());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(Integer.MAX_VALUE, reader.nextInt());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(0, reader.nextInt());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextFloat()) {
            assertEquals(5.3f, reader.nextFloat());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextFloat()) {
            assertEquals(123456789.12f, reader.nextFloat());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextLong()) {
            assertEquals( -9223372036854775800L, reader.nextLong());
        } else {
            throw new IllegalStateException("Fail was: " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextBigInteger()) {
            assertEquals(new BigDecimal("42e42").toBigIntegerExact(), reader.nextBigInteger());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextBigDecimal()) {
            assertEquals(new BigDecimal("42e-4242"), reader.nextBigDecimal());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.endObject();

        reader.close();
    }
}