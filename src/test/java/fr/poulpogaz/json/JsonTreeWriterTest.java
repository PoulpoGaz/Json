package fr.poulpogaz.json;

import fr.poulpogaz.json.tree.JsonArray;
import fr.poulpogaz.json.tree.JsonObject;
import fr.poulpogaz.json.tree.JsonTreeWriter;
import fr.poulpogaz.json.tree.JsonValue;
import fr.poulpogaz.json.tree.value.JsonString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;

/**
 * Test class for the {@link JsonTreeWriter} class
 *
 * @author PoulpoGaz
 * @version 1.2.1
 */
public class JsonTreeWriterTest {

    private JsonArray createArray() {
        JsonArray array = new JsonArray();
        array.addNull();
        array.add("hello");

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
        String expected = """
                {"number":1E+100,"array":[null,"hello",{"1":2,"null":null,"true":false,"hello":"world"}],"hello world":["h","e","l","l","o"," ","w","o","r","l","d"]}""";

        JsonObject object = createObject();
        object.put("array", createArray());

        StringWriter sw = new StringWriter();
        JsonTreeWriter.write(object, new JsonWriter(sw));

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void writeArray() throws IOException, JsonException {
        String expected = """
                [null,"hello",{"1":2,"null":null,"true":false,"hello":"world"},{"number":1E+100,"hello world":["h","e","l","l","o"," ","w","o","r","l","d"]},[null,"hello",{"1":2,"null":null,"true":false,"hello":"world"}]]""";
        
        JsonArray array = createArray();
        array.add(createObject());
        array.add(createArray());

        StringWriter sw = new StringWriter();
        JsonTreeWriter.write(array, new JsonWriter(sw));

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void writeValue() throws IOException, JsonException {
        JsonValue value = new JsonString("hello world");

        StringWriter sw = new StringWriter();
        JsonTreeWriter.write(value, new JsonWriter(sw));

        Assertions.assertEquals("\"hello world\"", sw.toString());
    }
}