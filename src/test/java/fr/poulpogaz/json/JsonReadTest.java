package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link JsonReader} class
 *
 * @author PoulpoGaz
 * @version 1.2.1
 */
public class JsonReadTest {

    @Test
    void read() throws IOException, JsonException {
        IJsonReader reader = new JsonReader(new StringReader(TestUtils.JSON));

        reader.beginObject();

        assertEquals(reader.nextKey(), "a");
        assertEquals(reader.nextString(), "b");

        assertEquals(reader.nextKey(), "c");
        assertEquals(reader.nextString(), "d");

        assertEquals(reader.nextKey(), "e");
        assertEquals(reader.nextInt(), 0);

        assertEquals(reader.nextKey(), "f");
        assertEquals(reader.nextBigDecimal(), new BigDecimal("46.43e-40"));

        assertEquals(reader.nextKey(), "g");
        assertEquals(reader.nextInt(), 50);

        reader.skipField();

        assertEquals(reader.nextKey(), "i");

        reader.beginArray();

        reader.beginArray();
        assertEquals(reader.nextString(), "a");

        reader.skipValue();
        assertEquals(reader.nextString(), "c");

        reader.endArray();
        reader.skipValue();

        reader.beginObject();

        assertEquals(reader.nextKey(), "g");
        assertTrue(reader.nextBoolean());

        assertEquals(reader.nextKey(), "h");
        assertFalse(reader.nextBoolean());

        assertEquals(reader.nextKey(), "i");
        reader.nextNull();

        assertEquals(reader.nextKey(), "j");
        reader.beginObject().endObject();

        assertEquals(reader.nextKey(), "k");
        reader.beginArray().endArray();

        reader.endObject();
        reader.endArray();
        reader.endObject();

        reader.close();
    }
}