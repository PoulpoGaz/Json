package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;

class JsonWriterTest {

    @Test
    void write() throws IOException, JsonException {
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

        System.out.println(sw);
    }
}