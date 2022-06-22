package fr.poulpogaz.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;

/**
 * Test class for the {@link JsonWriter} class
 *
 * @author PoulpoGaz
 * @version 1.2.1
 */
class JsonWriterTest {

    @Test
    void write() throws IOException, JsonException {
        String expected = """
                {"a":0,"b":"c","d":false,"e":true,"f":1.23456789E-42,"g":null,"object":{"a":"B\\n","0":0},"array":["a","b","c",["d","e","f"]]}""";

        StringWriter sw = new StringWriter();
        IJsonWriter writer = new JsonWriter(sw);

        writer.beginObject();

        writer.field("a", 0);
        writer.field("b", "c");
        writer.field("d", false);
        writer.field("e", true);
        writer.field("f", new BigDecimal("123456789E-50"));
        writer.nullField("g");

        writer.key("object").beginObject();
        writer.field("a", "\u0042\n");
        writer.field("0", 0);
        writer.endObject();

        writer.key("array");
        writer.beginArray();
        writer.value("a");
        writer.value("b");
        writer.value("c");
        writer.beginArray();
        writer.value("d");
        writer.value("e");
        writer.value("f");
        writer.endArray();
        writer.endArray();

        writer.endObject();

        writer.close();
        Assertions.assertEquals(expected, sw.toString());
    }
}