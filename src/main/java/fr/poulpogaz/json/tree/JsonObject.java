package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.util.HashMap;

public class JsonObject extends HashMap<String, JsonElement> implements JsonElement {

    public JsonObject(int initialCapacity) {
        super(initialCapacity);
    }

    public JsonObject() {
    }

    public void put(String key, Number value) {
        put(key, new JsonNumber(value));
    }

    public void put(String key, String value) {
        put(key, new JsonString(value));
    }

    public void put(String key, boolean value) {
        put(key, JsonBoolean.of(value));
    }

    public void putNull(String key) {
        put(key, JsonNull.INSTANCE);
    }

    public JsonNumber getAsNumber(String key) {
        JsonElement element = get(key);

        if (element.isValue()) {
            return ((JsonValue) element).isNumber() ? (JsonNumber) element : null;
        } else {
            return null;
        }
    }

    public JsonString getAsString(String key) {
        JsonElement element = get(key);

        if (element.isValue()) {
            return ((JsonValue) element).isString() ? (JsonString) element : null;
        } else {
            return null;
        }
    }

    public JsonBoolean getAsBoolean(String key) {
        JsonElement element = get(key);

        if (element.isValue()) {
            return ((JsonValue) element).isBoolean() ? (JsonBoolean) element : null;
        } else {
            return null;
        }
    }

    public JsonNull getAsNull(String key) {
        JsonElement element = get(key);

        if (element.isValue()) {
            return ((JsonValue) element).isNull() ? (JsonNull) element : null;
        } else {
            return null;
        }
    }

    public JsonObject getAsObject(String key) {
        JsonElement element = get(key);

        return element.isObject() ? (JsonObject) element : null;
    }

    public JsonArray getAsArray(String key) {
        JsonElement element = get(key);

        return element.isArray() ? (JsonArray) element : null;
    }

    @Override
    public boolean isObject() {
        return true;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isValue() {
        return false;
    }
}