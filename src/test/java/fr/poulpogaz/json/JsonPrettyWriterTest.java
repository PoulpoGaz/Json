package fr.poulpogaz.json;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class for the {@link JsonPrettyWriter} class
 *
 * @author PoulpoGaz
 * @version 1.2.1
 */
public class JsonPrettyWriterTest {

    @Test
    void write() throws IOException, JsonException {
        String expected = """
                {
                    "a": 0,
                    "b": "c",
                    "d": false,
                    "e": true,
                    "f": 1.23456789E-42,
                    "g": null,
                    "object": {
                        "a": "B\\n",
                        "0": 0
                    },
                    "array": [
                        "a",
                        "b",
                        "c",
                        [
                            "d",
                            "e",
                            "f"
                        ]
                    ]
                }""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);

        jpw.beginObject();

        jpw.field("a", 0);
        jpw.field("b", "c");
        jpw.field("d", false);
        jpw.field("e", true);
        jpw.field("f", new BigDecimal("123456789E-50"));
        jpw.nullField("g");

        jpw.key("object").beginObject();
        jpw.field("a", "\u0042\n");
        jpw.field("0", 0);
        jpw.endObject();

        jpw.key("array");
        jpw.beginArray();
        jpw.value("a");
        jpw.value("b");
        jpw.value("c");
        jpw.beginArray();
        jpw.value("d");
        jpw.value("e");
        jpw.value("f");
        jpw.endArray();
        jpw.endArray();

        jpw.endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void numberTest() throws IOException, JsonException {
        String expected = """
                {
                    "byte": 127,
                    "short": 32767,
                    "int": 2147483647,
                    "long": 9223372036854775807,
                    "float": 3.4028235E38,
                    "double": 1.7976931348623157E308,
                    "bigdec": 1.111111111111111111111111111111111111111111E-9958,
                    "bigint": 1111111111111111111111111111111111111111111111111111111111111,
                    "byte2": 127,
                    "short2": 32767,
                    "int2": 2147483647,
                    "long2": 9223372036854775807,
                    "float2": 3.4028235E38,
                    "double2": 1.7976931348623157E308,
                    "bigdec2": 1.111111111111111111111111111111111111111111E-9958,
                    "bigint2": 1111111111111111111111111111111111111111111111111111111111111
                }""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);

        jpw.beginObject();
        jpw.field("byte", Byte.MAX_VALUE);
        jpw.field("short", Short.MAX_VALUE);
        jpw.field("int", Integer.MAX_VALUE);
        jpw.field("long", Long.MAX_VALUE);
        jpw.field("float", Float.MAX_VALUE);
        jpw.field("double", Double.MAX_VALUE);
        jpw.field("bigdec", new BigDecimal("1111111111111111111111111111111111111111111e-10000"));
        jpw.field("bigint", new BigInteger("1111111111111111111111111111111111111111111111111111111111111"));


        jpw.key("byte2").value(Byte.MAX_VALUE);
        jpw.key("short2").value(Short.MAX_VALUE);
        jpw.key("int2").value(Integer.MAX_VALUE);
        jpw.key("long2").value(Long.MAX_VALUE);
        jpw.key("float2").value(Float.MAX_VALUE);
        jpw.key("double2").value(Double.MAX_VALUE);
        jpw.key("bigdec2").value(new BigDecimal("1111111111111111111111111111111111111111111e-10000"));
        jpw.key("bigint2").value(new BigInteger("1111111111111111111111111111111111111111111111111111111111111"));

        jpw.endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void inlineArray() throws JsonException, IOException {
        String expected = """
                ["a", 0, null, -5, ["h"],
                {
                    "hello": "world"
                }]""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);
        jpw.setInline(JsonPrettyWriter.Inline.ARRAY);

        jpw.beginArray()
                .value("a")
                .value(0)
                .nullValue()
                .value(-5)
                .beginArray().value("h").endArray()
                .beginObject().field("hello", "world").endObject()
                .endArray();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void inlineObject() throws JsonException, IOException {
        String expected =  """
                {"hello": "world", "test": 5, "array": [
                    "a",
                    "b",
                    "c"
                ], "obj": {"obj2": {"obj3": {"a": "b", "b": [
                    "aa",
                    "a"
                ]}}}, "bla": "bla"}""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);
        jpw.setInline(JsonPrettyWriter.Inline.OBJECT);

        jpw.beginObject()
                .field("hello", "world")
                .field("test", 5)
                .key("array").beginArray()
                .value("a")
                .value("b")
                .value("c")
                .endArray()
                .key("obj").beginObject()
                    .key("obj2").beginObject()
                        .key("obj3").beginObject()
                        .field("a", "b")
                        .key("b").beginArray().value("aa").value("a").endArray()
                        .endObject()
                    .endObject()
                .endObject()
                .field("bla", "bla")
                .endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void inlineAll() throws JsonException, IOException {
        String expected = """
                {"hello": "hello", "key": ["a", "b", "c", null], "42": 42}""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);
        jpw.setInline(JsonPrettyWriter.Inline.ALL);

        jpw.beginObject()
                .field("hello", "hello")
                .key("key").beginArray()
                    .value("a")
                    .value("b")
                    .value("c")
                    .nullValue()
                .endArray()
                .field("42", 42)
                .endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void enableAndDisableInline1() throws JsonException, IOException {
        String expected = """
                {
                    "key": "value",
                    "array": [
                        "a",
                        "b", "c", "d"
                    ]
                }""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);

        jpw.beginObject()
                .field("key", "value")
                .key("array").beginArray();

        jpw.value("a");
        jpw.value("b");
        jpw.setInline(JsonPrettyWriter.Inline.ARRAY);
        jpw.value("c");
        jpw.value("d");
        jpw.setInline(JsonPrettyWriter.Inline.NONE);
        jpw.endArray();
        jpw.endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void enableAndDisableInline2() throws JsonException, IOException {
        String expected = """
                {"key": "value", "array": [
                    "a",
                    "b", "c", "d"
                    ]
                }""";

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);
        jpw.setInline(JsonPrettyWriter.Inline.OBJECT);

        jpw.beginObject()
                .field("key", "value")
                .key("array").beginArray();

        jpw.value("a");
        jpw.value("b");
        jpw.setInline(JsonPrettyWriter.Inline.ARRAY);
        jpw.value("c");
        jpw.value("d");
        jpw.setInline(JsonPrettyWriter.Inline.NONE);
        jpw.endArray();
        jpw.endObject();

        jpw.close();

        Assertions.assertEquals(expected, sw.toString());
    }

    @Test
    void enableAndDisableInline3() throws JsonException, IOException {

        StringWriter sw = new StringWriter();
        JsonPrettyWriter jpw = new JsonPrettyWriter(sw);
        jpw.setUseWindowsLineSeparator(false);
        jpw.setInline(JsonPrettyWriter.Inline.ALL);

        jpw.beginObject()
                .key("key").beginObject()
                        .key("key2").beginObject()
                            .key("key3").beginObject();

        jpw.setInline(JsonPrettyWriter.Inline.NONE);
        jpw.field("hello", "world");
        jpw.endObject().endObject().endObject().endObject();

        jpw.close();

        System.out.println(sw.toString());

        //Assertions.assertEquals(expected, sw.toString());
    }
}