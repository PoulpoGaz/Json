package fr.poulpogaz.json.tree;

import org.junit.jupiter.api.Test;

import static fr.poulpogaz.json.TestUtils.BIG_INT;
import static fr.poulpogaz.json.TestUtils.createObject;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the {@link JsonObject} class
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public class JsonObjectTest {

    @Test
    void getAsJsonNumber() {
        JsonObject o = createObject();

        assertNotNull(o.getAsJsonNumber("byte"));
        assertNotNull(o.getAsJsonNumber("short"));
        assertNotNull(o.getAsJsonNumber("int"));
        assertNotNull(o.getAsJsonNumber("long"));
        assertNotNull(o.getAsJsonNumber("float"));
        assertNotNull(o.getAsJsonNumber("double"));
        assertNotNull(o.getAsJsonNumber("bigint"));
        assertNotNull(o.getAsJsonNumber("bigdec"));

        assertNull(o.getAsJsonNumber("abcd"));
        assertNull(o.getAsJsonNumber("string"));
        assertNull(o.getAsJsonNumber("boolean"));
        assertNull(o.getAsJsonNumber("null"));
    }

    @Test
    void getAsJsonString() {
        JsonObject o = createObject();

        assertNotNull(o.getAsJsonString("string"));

        assertNull(o.getAsJsonString("byte"));
        assertNull(o.getAsJsonString("short"));
        assertNull(o.getAsJsonString("int"));
        assertNull(o.getAsJsonString("long"));
        assertNull(o.getAsJsonString("float"));
        assertNull(o.getAsJsonString("double"));
        assertNull(o.getAsJsonString("bigint"));
        assertNull(o.getAsJsonString("bigdec"));
        assertNull(o.getAsJsonString("abcd"));
        assertNull(o.getAsJsonString("boolean"));
        assertNull(o.getAsJsonString("null"));
    }

    @Test
    void getAsJsonBoolean() {
        JsonObject o = createObject();

        assertNotNull(o.getAsJsonBoolean("boolean"));

        assertNull(o.getAsJsonBoolean("byte"));
        assertNull(o.getAsJsonBoolean("short"));
        assertNull(o.getAsJsonBoolean("int"));
        assertNull(o.getAsJsonBoolean("long"));
        assertNull(o.getAsJsonBoolean("float"));
        assertNull(o.getAsJsonBoolean("double"));
        assertNull(o.getAsJsonBoolean("bigint"));
        assertNull(o.getAsJsonBoolean("bigdec"));

        assertNull(o.getAsJsonBoolean("abcd"));
        assertNull(o.getAsJsonBoolean("string"));
        assertNull(o.getAsJsonBoolean("null"));
    }

    @Test
    void getOptionalJsonNumber() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalJsonNumber("boolean").isEmpty());
        assertTrue(o.getOptionalJsonNumber("byte").isPresent());
    }

    @Test
    void getOptionalNumber() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalInt("string").isEmpty());
        assertTrue(o.getOptionalInt("int").isPresent());
    }

    @Test
    void getOptionalJsonBoolean() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalJsonBoolean("boolean").isPresent());
        assertTrue(o.getOptionalJsonBoolean("null").isEmpty());
    }

    @Test
    void getOptionalBoolean() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalBoolean("boolean").isPresent());
        assertTrue(o.getOptionalBoolean("null").isEmpty());
    }

    @Test
    void getOptionalJsonString() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalString("boolean").isPresent());
        assertTrue(o.getOptionalString("string").isPresent());
    }

    @Test
    void getOptionalString() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalString("boolean").isPresent());
        assertTrue(o.getOptionalString("string").isPresent());
    }

    @Test
    void getOptionalJsonNull() {
        JsonObject o = createObject();

        assertTrue(o.getOptionalJsonNull("null").isPresent());
        assertTrue(o.getOptionalJsonNull("string").isEmpty());
    }

    @Test
    void getAsBigInteger() {
        JsonObject o = createObject();

        assertEquals(o.getAsBigInteger("bigint"), BIG_INT);
    }
}
