package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Optional;

/**
 * A class that represents a json object.
 * This class is based on the {@link HashMap}
 * class
 *
 * @author PoulpoGaz
 * @version 1.1
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
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonNumber getAsJsonNumber(String key) {
        JsonElement element = get(key);

        return element.isNumber() ? (JsonNumber) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonString} associated with the specified key
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonString getAsJsonString(String key) {
        JsonElement element = get(key);

        return element.isString() ? (JsonString) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonBoolean} associated with the specified key
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonBoolean getAsJsonBoolean(String key) {
        JsonElement element = get(key);

        return element.isBoolean() ? (JsonBoolean) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonNull} associated with the specified key
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonValue}
     */
    public JsonNull getAsJsonNull(String key) {
        JsonElement element = get(key);

        return element.isNull() ? (JsonNull) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonObject} associated with the specified key
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonObject}
     */
    public JsonObject getAsObject(String key) {
        JsonElement element = get(key);

        return element.isObject() ? (JsonObject) element : null;
    }

    /**
     * @param key the specified key
     * @return the {@link JsonArray} associated with the specified key
     *          or {@code null} if the key doesn't exist, or if the value
     *          isn't a {@link JsonArray}
     */
    public JsonArray getAsArray(String key) {
        JsonElement element = get(key);

        return element.isArray() ? (JsonArray) element : null;
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonNumber} associated with the specified key
     * or an empty {@link Optional} if the key doesn't exist, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonNumber> getOptionalJsonNumber(String key) {
        JsonElement element = get(key);

        if (element != null && element.isNumber()) {
            return Optional.of((JsonNumber) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonString} associated with the specified key
     * or an empty {@link Optional} if the key doesn't exist, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonString> getOptionalJsonString(String key) {
        JsonElement element = get(key);

        if (element != null && element.isString()) {
            return Optional.of((JsonString) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonBoolean} associated with the specified key
     * or an empty {@link Optional} if the key doesn't exist, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonBoolean> getOptionalJsonBoolean(String key) {
        JsonElement element = get(key);

        if (element != null && element.isBoolean()) {
            return Optional.of((JsonBoolean) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonNull} associated with the specified key
     *  or an empty {@link Optional} if the key doesn't exist, or if the value
     *  isn't a {@link JsonValue}
     */
    public Optional<JsonNull> getOptionalJsonNull(String key) {
        JsonElement element = get(key);

        if (element != null && element.isNull()) {
            return Optional.of((JsonNull) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonObject} associated with the specified key
     * or an empty {@link Optional} if the key doesn't exist, or if the value
     * isn't a {@link JsonObject}
     */
    public Optional<JsonObject> getOptionalJsonObject(String key) {
        JsonElement element = get(key);

        if (element != null && element.isObject()) {
            return Optional.of((JsonObject) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing {@link JsonArray} associated with the specified key
     * or an empty {@link Optional} if the key doesn't exist, or if the value
     * isn't a {@link JsonArray}
     */
    public Optional<JsonArray> getOptionalJsonArray(String key) {
        JsonElement element = get(key);

        if (element != null && element.isArray()) {
            return Optional.of((JsonArray) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code byte} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code byte}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public byte getAsByte(String key) {
        return get(key).getAsByte();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code short} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code short}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public short getAsShort(String key) {
        return get(key).getAsShort();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code int} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code int}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public int getAsInt(String key) {
        return get(key).getAsInt();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code long} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code long}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public long getAsLong(String key) {
        return get(key).getAsLong();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@link BigInteger} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link BigInteger}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public BigInteger getAsBigInteger(String key) {
        return get(key).getAsBigInteger();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code float} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code float}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public float getAsFloat(String key) {
        return get(key).getAsFloat();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code double} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code double}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public double getAsDouble(String key) {
        return get(key).getAsDouble();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@link BigDecimal} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link BigDecimal}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public BigDecimal getAsBigDecimal(String key) {
        return get(key).getAsBigDecimal();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@link String} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link String}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public String getAsString(String key) {
        return get(key).getAsString();
    }

    /**
     * @param key the specified key
     * @return the value associated with the specified key as {@code boolean} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code boolean}
     * @throws NullPointerException if there is not {@link JsonElement} associated with the key
     */
    public boolean getAsBoolean(String key) {
        return get(key).getAsBoolean();
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code byte}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a byte
     */
    public Optional<Byte> getOptionalByte(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalByte();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code short}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code short}
     */
    public Optional<Short> getOptionalShort(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalShort();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code int}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code int}
     */
    public Optional<Integer> getOptionalInt(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalInt();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code long}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code long}
     */
    public Optional<Long> getOptionalLong(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalLong();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@link BigInteger}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@link BigInteger}
     */
    public Optional<BigInteger> getOptionalBigInteger(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalBigInteger();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code float}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code float}
     */
    public Optional<Float> getOptionalFloat(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalFloat();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code double}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code double}
     */
    public Optional<Double> getOptionalDouble(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalDouble();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@link BigDecimal}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@link BigDecimal}
     */
    public Optional<BigDecimal> getOptionalBigDecimal(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalBigDecimal();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@link String}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@link String}
     */
    public Optional<String> getOptionalString(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalString();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param key the specified key
     * @return an {@link Optional} describing the value associated with the specified key as {@code boolean}
     * or an empty {@link Optional} if there is no value associated with the key or if the json element
     * is not a {@code boolean}
     */
    public Optional<Boolean> getOptionalBoolean(String key) {
        JsonElement e = get(key);

        if (e != null) {
            return e.optionalBoolean();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonObject}
     */
    @Override
    public boolean isObject() {
        return true;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonArray}
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonValue}
     */
    @Override
    public boolean isValue() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNumber}
     */
    @Override
    public boolean isNumber() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonString}
     */
    @Override
    public boolean isString() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonBoolean}
     */
    @Override
    public boolean isBoolean() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNull}
     */
    @Override
    public boolean isNull() {
        return false;
    }

    /**
     * @return this value as {@code byte} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code byte}
     */
    @Override
    public byte getAsByte() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code short} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code short}
     */
    @Override
    public short getAsShort() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code int} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code int}
     */
    @Override
    public int getAsInt() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code long} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code long}
     */
    @Override
    public long getAsLong() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@link BigInteger} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigInteger}
     */
    @Override
    public BigInteger getAsBigInteger() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code float} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code float}
     */
    @Override
    public float getAsFloat() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code double} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code double}
     */
    @Override
    public double getAsDouble() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@link BigDecimal} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigDecimal}
     */
    @Override
    public BigDecimal getAsBigDecimal() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@link String} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link String}
     */
    @Override
    public String getAsString() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return this value as {@code boolean} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws UnsupportedOperationException if it can't be parsed to a {@code boolean}
     */
    @Override
    public boolean getAsBoolean() {
        throw new IllegalStateException("Not a JsonValue");
    }

    /**
     * @return an {@link Optional} describing a {@code byte},
     * or an empty {@link Optional} if this json element isn't a {@code byte}
     */
    @Override
    public Optional<Byte> optionalByte() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code short},
     * or an empty {@link Optional} if this json element isn't a {@code short}
     */
    @Override
    public Optional<Short> optionalShort() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code int},
     * or an empty {@link Optional} if this json element isn't a {@code int}
     */
    @Override
    public Optional<Integer> optionalInt() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code long},
     * or an empty {@link Optional} if this json element isn't a {@code long}
     */
    @Override
    public Optional<Long> optionalLong() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link BigInteger},
     * or an empty {@link Optional} if this json element isn't a {@link BigInteger}
     */
    @Override
    public Optional<BigInteger> optionalBigInteger() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code float},
     * or an empty {@link Optional} if this json element isn't a {@code float}
     */
    @Override
    public Optional<Float> optionalFloat() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code double},
     * or an empty {@link Optional} if this json element isn't a {@code double}
     */
    @Override
    public Optional<Double> optionalDouble() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link BigDecimal},
     * or an empty {@link Optional} if this json element isn't a {@link BigDecimal}
     */
    @Override
    public Optional<BigDecimal> optionalBigDecimal() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link String},
     * or an empty {@link Optional} if this json element isn't a {@link String}
     */
    @Override
    public Optional<String> optionalString() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code boolean},
     * or an empty {@link Optional} if this json element isn't a {@code boolean}
     */
    @Override
    public Optional<Boolean> optionalBoolean() {
        return Optional.empty();
    }
}