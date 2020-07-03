package fr.poulpogaz.json;

import fr.poulpogaz.json.tree.JsonArray;
import fr.poulpogaz.json.tree.JsonObject;
import fr.poulpogaz.json.tree.JsonTreeWriter;
import fr.poulpogaz.json.tree.JsonValue;
import fr.poulpogaz.json.tree.value.JsonString;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class for the {@link JsonTreeWriter} class
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonTreeWriterTest {

    private JsonArray createArray() {
        JsonArray array = new JsonArray();

        for (int i = 0; i < 30; i++) {
            array.add(i);
        }

        JsonObject inner = new JsonObject();

        inner.put("hello", "world");
        inner.put("1", 2);
        inner.put("true", false);
        inner.putNull("null");

        array.add(inner);

        return array;
    }

    private JsonObject createObject() {
        JsonObject object = new JsonObject();

        object.put("number", new BigDecimal("1e100"));

        JsonArray array = new JsonArray();

        array.add("h");
        array.add("e");
        array.add("l");
        array.add("l");
        array.add("o");
        array.add(" ");
        array.add("w");
        array.add("o");
        array.add("r");
        array.add("l");
        array.add("d");

        object.put("hello world", array);

        return object;
    }

    @Test
    void writeObject() throws IOException, JsonException {
        JsonObject object = createObject();

        object.put("array", createArray());

        JsonTreeWriter.write(object, new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_tree_writer_test_object.json"))));
    }

    @Test
    void writeArray() throws IOException, JsonException {
        JsonArray array = createArray();
        array.add(createObject());
        array.add(createArray());

        JsonTreeWriter.write(array, new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_tree_writer_test_array.json"))));
    }

    @Test
    void writeValue() throws IOException, JsonException {
        JsonValue value = new JsonString("hello world");

        JsonTreeWriter.write(value, new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_tree_writer_test_value.json"))));
    }
}