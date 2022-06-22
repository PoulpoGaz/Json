package fr.poulpogaz.json.tree;

import org.junit.jupiter.api.Test;

import static fr.poulpogaz.json.TestUtils.BIG_INT;
import static fr.poulpogaz.json.TestUtils.createArray;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link JsonArray} class
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public class JsonArrayTest {

    @Test
    void getAsJsonNumber() {
        JsonArray a = createArray();

        assertNotNull(a.getAsJsonNumber(0));
        assertNotNull(a.getAsJsonNumber(1));
        assertNotNull(a.getAsJsonNumber(2));
        assertNotNull(a.getAsJsonNumber(3));
        assertNotNull(a.getAsJsonNumber(4));
        assertNotNull(a.getAsJsonNumber(5));
        assertNotNull(a.getAsJsonNumber(6));
        assertNotNull(a.getAsJsonNumber(7));

        assertNull(a.getAsJsonNumber(10000));
        assertNull(a.getAsJsonNumber(8));
        assertNull(a.getAsJsonNumber(9));
        assertNull(a.getAsJsonNumber(10));
    }

    @Test
    void getAsJsonString() {
        JsonArray a = createArray();

        assertNotNull(a.getAsJsonString(8));

        assertNull(a.getAsJsonString(0));
        assertNull(a.getAsJsonString(1));
        assertNull(a.getAsJsonString(2));
        assertNull(a.getAsJsonString(3));
        assertNull(a.getAsJsonString(4));
        assertNull(a.getAsJsonString(5));
        assertNull(a.getAsJsonString(6));
        assertNull(a.getAsJsonString(7));
        assertNull(a.getAsJsonString(10000));
        assertNull(a.getAsJsonString(9));
        assertNull(a.getAsJsonString(10));
    }

    @Test
    void getAsJsonBoolean() {
        JsonArray a = createArray();

        assertNotNull(a.getAsJsonBoolean(9));

        assertNull(a.getAsJsonBoolean(0));
        assertNull(a.getAsJsonBoolean(1));
        assertNull(a.getAsJsonBoolean(2));
        assertNull(a.getAsJsonBoolean(3));
        assertNull(a.getAsJsonBoolean(4));
        assertNull(a.getAsJsonBoolean(5));
        assertNull(a.getAsJsonBoolean(6));
        assertNull(a.getAsJsonBoolean(7));

        assertNull(a.getAsJsonBoolean(10000));
        assertNull(a.getAsJsonBoolean(8));
        assertNull(a.getAsJsonBoolean(10));
    }

    @Test
    void getOptionalJsonNumber() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalJsonNumber(9).isEmpty());
        assertTrue(a.getOptionalJsonNumber(0).isPresent());
    }

    @Test
    void getOptionalNumber() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalInt(8).isEmpty());
        assertTrue(a.getOptionalInt(2).isPresent());
    }

    @Test
    void getOptionalJsonBoolean() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalJsonBoolean(9).isPresent());
        assertTrue(a.getOptionalJsonBoolean(10).isEmpty());
    }

    @Test
    void getOptionalBoolean() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalBoolean(9).isPresent());
        assertTrue(a.getOptionalBoolean(10).isEmpty());
    }

    @Test
    void getOptionalJsonString() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalString(9).isPresent());
        assertTrue(a.getOptionalString(8).isPresent());
    }

    @Test
    void getOptionalString() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalString(9).isPresent());
        assertTrue(a.getOptionalString(8).isPresent());
    }

    @Test
    void getOptionalJsonNull() {
        JsonArray a = createArray();

        assertTrue(a.getOptionalJsonNull(10).isPresent());
        assertTrue(a.getOptionalJsonNull(8).isEmpty());
    }

    @Test
    void getAsBigInteger() {
        JsonArray a = createArray();

        assertEquals(a.getAsBigInteger(6), BIG_INT);
    }
}
