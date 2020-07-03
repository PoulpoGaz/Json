package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonPrettyWriterTest {

    @Test
    void write() throws IOException, JsonException {
        IJsonWriter writer = new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_pretty_writer_test.json")));

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
    }
}