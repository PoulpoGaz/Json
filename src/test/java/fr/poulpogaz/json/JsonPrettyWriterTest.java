package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class for the {@link JsonPrettyWriter} class
 *
 * @author PoulpoGaz
 * @version 1.0
 */
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

    @Test
    void inlineArray() throws IOException, JsonException {
        JsonPrettyWriter writer = new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_pretty_writer_test_inline.json")));
        writer.setInlineArray(true);

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

        writer.beginObject();
        writer.field("hello", "world");
        writer.field("world", "hello");
        writer.field("lorem", "ipsum");
        writer.field("42", 42);
        writer.endObject();

        writer.endArray();
        writer.endArray();

        writer.field("hello", "world");

        writer.endObject();

        writer.close();
    }

    @Test
    void numberTest() throws IOException, JsonException {
        JsonPrettyWriter writer = new JsonPrettyWriter(Files.newBufferedWriter(Path.of("src/test/json_pretty_writer_test_number.json")));

        writer.beginObject();
        writer.field("byte", Byte.MAX_VALUE);
        writer.field("short", Short.MAX_VALUE);
        writer.field("int", Integer.MAX_VALUE);
        writer.field("long", Long.MAX_VALUE);
        writer.field("float", Float.MAX_VALUE);
        writer.field("double", Double.MAX_VALUE);
        writer.field("bigdec", new BigDecimal("1111111111111111111111111111111111111111111e-10000"));
        writer.field("bigint", new BigInteger("1111111111111111111111111111111111111111111111111111111111111"));


        writer.key("byte2").value(Byte.MAX_VALUE);
        writer.key("short2").value(Short.MAX_VALUE);
        writer.key("int2").value(Integer.MAX_VALUE);
        writer.key("long2").value(Long.MAX_VALUE);
        writer.key("float2").value(Float.MAX_VALUE);
        writer.key("double2").value(Double.MAX_VALUE);
        writer.key("bigdec2").value(new BigDecimal("1111111111111111111111111111111111111111111e-10000"));
        writer.key("bigint2").value(new BigInteger("1111111111111111111111111111111111111111111111111111111111111"));

        writer.endObject();

        writer.close();
    }
}