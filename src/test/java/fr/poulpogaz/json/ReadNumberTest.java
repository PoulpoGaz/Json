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
            assertEquals(reader.nextInt(), 50);
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(reader.nextInt(), Integer.MAX_VALUE);
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextInt()) {
            assertEquals(reader.nextInt(), 0);
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextFloat()) {
            assertEquals(reader.nextFloat(), 5.3f);
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextFloat()) {
            assertEquals(reader.nextFloat(), 123456789.12f);
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextLong()) {
            assertEquals(reader.nextLong(), -9223372036854775800L);
        } else {
            throw new IllegalStateException("Fail was: " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextBigInteger()) {
            assertEquals(reader.nextBigInteger(), new BigDecimal("42e42").toBigIntegerExact());
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.nextKey();
        if (reader.hasNextBigDecimal()) {
            assertEquals(reader.nextBigDecimal(), new BigDecimal("42e-4242"));
        } else {
            throw new IllegalStateException("Fail was " + reader.next());
        }

        reader.endObject();

        reader.close();
    }
}