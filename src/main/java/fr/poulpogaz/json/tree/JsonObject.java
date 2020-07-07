package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.util.HashMap;

/**
 * A class that represents a json object.
 * This class is based on the {@link HashMap}
 * class
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonObject extends HashMap<String, JsonElement> implements JsonElement {

    /**
     * Creates a new {@code JsonObject}
     *
     * @param initialCapacity the initial capacity for this object
     */
    public JsonObject(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Creates a new {@code JsonObject}
     */
    public JsonObject() {
    }

    /**
     * Associate this key to this value, which is wrap
     * in a {@link JsonNumber}
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public JsonElement put(String key, Number value) {
        return put(key, new JsonNumber(value));
    }
    /**
     * Associate this key to this value, which is wrap
     * in a {@link JsonString}
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */

    public JsonElement put(String key, String value) {
        return put(key, new JsonString(value));
    }

    /**
     * Associate this key to this value, which is wrap
     * in a {@link JsonBoolean}
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public JsonElement put(String key, boolean value) {
        return put(key, JsonBoolean.of(value));
    }

    /**
     * Associate this key to the {@link JsonNull} value
     *
     * @param key key with which the null value is to be associated
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with {@code key}.)
     */
    public JsonElement putNull(String key) {
        return put(key, JsonNull.INSTANCE);
    }

    /**
     * @param key the specified key
     * @return the {@link JsonNumber} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonNumber getAsJsonNumber(String key) {
        JsonElement element = get(key);

        if (element == null) {
            return null;
        } else if (element.isValue()) {
            return ((JsonValue) element).isNumber() ? (JsonNumber) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param key the specified key
     * @return the {@link JsonString} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonString getAsJsonString(String key) {
        JsonElement element = get(key);

        if (element == null) {
            return null;
        } else if (element.isValue()) {
            return ((JsonValue) element).isString() ? (JsonString) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param key the specified key
     * @return the {@link JsonBoolean} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonBoolean getAsJsonBoolean(String key) {
        JsonElement element = get(key);

        if (element == null) {
            return null;
        } else if (element.isValue()) {
            return ((JsonValue) element).isBoolean() ? (JsonBoolean) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param key the specified key
     * @return the {@link JsonNull} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonNull getAsJsonNull(String key) {
        JsonElement element = get(key);

        if (element == null) {
            return null;
        } else if (element.isValue()) {
            return ((JsonValue) element).isNull() ? (JsonNull) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param key the specified key
     * @return the {@link JsonObject} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonObject}
     */
    public JsonObject getAsObject(String key) {
        JsonElement element = get(key);

        return element == null ? null : element.isObject() ? (JsonObject) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonArray} associated with the specified key
     *          or {@code null} if the key doesn't exits, or if the value
     *          isn't a {@link JsonArray}
     */
    public JsonArray getAsArray(String key) {
        JsonElement element = get(key);

        return element == null ? null : element.isArray() ? (JsonArray) element : null;
    }

    /**
     * @return true as this class represents an object
     */
    @Override
    public boolean isObject() {
        return true;
    }

    /**
     * @return false as this class represents a value and not an array
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * @return false as this class represents a value and not a value
     */
    @Override
    public boolean isValue() {
        return false;
    }
}