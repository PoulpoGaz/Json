# json [![License](https://img.shields.io/badge/license-MIT-red)](LICENSE)

Another java json library.

## How to use

### 1 - Stream api

You can use the JsonReader object for reading and the JsonWriter object for writing.

#### A - JsonReader

For reading a file as following:
```json
{
    "key 1": "value",
    "key 2": 42e42,
    "key 3": false,
    "key 4": null,
    "key 5": [
      "h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d"
    ]
}
```
You can do
```java
IJsonReader reader = new JsonReader(getReader()); // creates a new reader

reader.beginObject(); // begins reading an object: skips whitespace and reads the char '{'

String key1 = reader.nextKey(); // gets the next key, here "key 1"
String value1 = reader.nextString(); // and gets his value: "value"

String key2 = reader.nextKey();
BigInteger _42e42_ = reader.nextBigInteger();

String key3 = reader.nextKey();
boolean _false_ = reader.nextBoolean();

reader.skipField(); // You can skip field, value or key

String key5 = reader.nextKey();

reader.beginArray(); // begins reading an array, skips whitespace and reads the char '['

while (!reader.isArrayEnd()) { // Read all elements in the array
    System.out.print(reader.nextString());
}

reader.endArray(); // ends reading an array, skips whitespace and reads the char ']'

reader.endObject(); // ends reading an object: skips whitespace and reads the char '}'
reader.close(); // closes the stream
```

#### B - JsonWriter

For writing this json:
```json
{
    "key 1": "value",
    "key 2": 42e42,
    "key 3": false,
    "key 4": null,
    "key 5": [
      "h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d"
    ]
}
```
You can do
```java
IJsonWriter writer = new JsonWriter(getWriter()); // creates a new writer

writer.beginObject(); // writes '{' and begins an object

writer.key("key 1").value("value");

// same as
// writer.key("key 2").value(new BigDecimal("42e42").toBigIntegerExact));
writer.field("key 2", new BigDecimal("42e42").toBigIntegerExact());
writer.field("key 3", false);
writer.nullField("key 4");

writer.key("key 5");

writer.beginArray(); // begins reading an array, writes the char '['

String[] hello_world = new String[] {"h", "e", "l", "l", "o", " ", "w", "o", "r", "l", "d"};

for (String s : hello_world) {
    writer.value(s);
}

writer.endArray(); // ends reading an array, writes the char ']'

writer.endObject(); // ends reading an object: writes the char '}'
writer.close(); // closes the stream
```

For writing pretty json. Just use the JsonPrettyWriter object. He has the same usage as
JsonWriter.

### 2 - Tree api

#### A - Reading

You can use the class JsonTreeReader for reading a json stream and get a tree representation of his json.

#### B - Writing

You can use the class JsonTreeWriter for writing a JsonElement.

## Other information

This library doesn't support data binding.

### Maven

json is available at maven central.

```xml
<dependency>
    <groupId>io.github.poulpogaz</groupId>
    <artifactId>json</artifactId>
    <version>1.2.2</version>
</dependency>
```
