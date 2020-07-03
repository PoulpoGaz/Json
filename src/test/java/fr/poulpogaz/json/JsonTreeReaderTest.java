package fr.poulpogaz.json;

import fr.poulpogaz.json.tree.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTreeReaderTest {

    private Reader getReader() throws IOException {
        return Files.newBufferedReader(Path.of("src/test/java/resources/read_test.json"));
    }

    @Test
    void read() throws IOException, JsonException {
        JsonElement element = JsonTreeReader.read(getReader());

        assertTrue(element instanceof JsonObject);

        JsonObject object = (JsonObject) element;
        assertNotNull(object.get("a"));
        assertEquals(((JsonValue) object.get("a")).getAsString(), "b");

        assertNotNull(object.get("c"));
        assertEquals(((JsonValue) object.get("c")).getAsString(), "d");

        assertNotNull(object.get("e"));
        assertEquals(((JsonValue) object.get("e")).getAsInt(), 0);

        assertNotNull(object.get("f"));
        assertEquals(((JsonValue) object.get("f")).getAsBigDecimal(), new BigDecimal("46.43e-40"));

        assertNotNull(object.get("g"));
        assertEquals(((JsonValue) object.get("g")).getAsInt(), 50);

        assertNotNull(object.get("h"));
        assertTrue(((JsonValue) object.get("h")).isNull());

        assertNotNull(object.get("i"));
        JsonArray array = object.getAsArray("i");

        checkStringArray(array.getAsArray(0), new String[]{"a", "b", "c"});
        checkStringArray(array.getAsArray(1), new String[]{"d", "e", "f"});

        JsonObject object1 = array.getAsObject(2);

        assertNotNull(object1.get("g"));
        assertTrue(object1.getAsBoolean("g").getAsBoolean());

        assertNotNull(object1.get("h"));
        assertFalse(object1.getAsBoolean("h").getAsBoolean());

        assertNotNull(object1.get("i"));
        assertTrue(object1.getAsNull("i").isNull());

        assertNotNull(object1.get("j"));
        assertEquals(object1.getAsObject("j").size(), 0);

        assertNotNull(object1.get("k"));
        assertEquals(object1.getAsArray("k").size(), 0);
    }

    private void checkStringArray(JsonArray array, String[] strings) {
        for (int i = 0; i < array.size(); i++) {
            assertEquals(array.getAsString(i).getAsString(), strings[i]);
        }
    }
}