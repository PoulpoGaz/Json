package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.util.ArrayList;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonArray extends ArrayList<JsonElement> implements JsonElement {

    public JsonArray(int initialCapacity) {
        super(initialCapacity);
    }

    public JsonArray() {
    }

    public void add(String value) {
        add(new JsonString(value));
    }

    public void add(Number value) {
        add(new JsonNumber(value));
    }

    public void add(boolean value) {
        add(JsonBoolean.of(value));
    }

    public void addNull() {
        add(JsonNull.INSTANCE);
    }

    public JsonNumber getAsJsonNumber(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isNumber() ? (JsonNumber) element : null;
        } else {
            return null;
        }
    }

    public JsonString getAsJsonString(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isString() ? (JsonString) element : null;
        } else {
            return null;
        }
    }

    public JsonBoolean getAsJsonBoolean(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isBoolean() ? (JsonBoolean) element : null;
        } else {
            return null;
        }
    }

    public JsonNull getAsJsonNull(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isNull() ? (JsonNull) element : null;
        } else {
            return null;
        }
    }

    public JsonObject getAsObject(int index) {
        JsonElement element = get(index);

        return element.isObject() ? (JsonObject) element : null;
    }

    public JsonArray getAsArray(int index) {
        JsonElement element = get(index);

        return element.isArray() ? (JsonArray) element : null;
    }
    
    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isValue() {
        return false;
    }
}