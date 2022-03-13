package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

/**
 * A class that represents a json array.
 *
 * Note:
 * This class doesn't override the {@link ArrayList}
 * to avoid {@code null}
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public class JsonArray implements JsonElement, Iterable<JsonElement> {

    private final ArrayList<JsonElement> delegate;

    /**
     * Creates a new {@code JsonArray}
     *
     * @param initialCapacity the initial capacity for this array
     * @see ArrayList#ArrayList(int)
     */
    public JsonArray(int initialCapacity) {
        delegate = new ArrayList<>(initialCapacity);
    }

    /**
     * Creates a new {@code JsonObject}
     * @see ArrayList#ArrayList()
     */
    public JsonArray() {
        delegate = new ArrayList<>();
    }

    /**
     * Wraps the specified value in a {@link JsonString}
     * and adds it to the list.
     *
     * @param value the value to add
     */
    public void add(String value) {
        add(new JsonString(value));
    }

    /**
     * Wraps the specified value in a {@link JsonNumber}
     * and adds it to the list.
     *
     * @param value the value to add
     */
    public void add(Number value) {
        add(new JsonNumber(value));
    }

    /**
     * Wraps the specified value in a {@link JsonBoolean}
     * and adds it to the list.
     *
     * @param value the value to add
     */
    public void add(boolean value) {
        add(JsonBoolean.of(value));
    }

    /**
     * Adds the specified value to the list.
     *
     * @param element the value to add
     * @see ArrayList#add(Object)
     */
    public void add(JsonElement element) {
        delegate.add(Objects.requireNonNull(element));
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @see ArrayList#get(int)
     */
    public JsonElement get(int index) {
        return delegate.get(index);
    }

    /**
     * @return the number of elements in this list
     * @see ArrayList#size()
     */
    public int size() {
        return delegate.size();
    }

    /**
     * Adds the {@link JsonNull} value to the list.
     */
    public void addNull() {
        add(JsonNull.INSTANCE);
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonNumber} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonNumber}
     */
    public JsonNumber getAsJsonNumber(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isNumber() ? (JsonNumber) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonString} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonString}
     */
    public JsonString getAsJsonString(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isString() ? (JsonString) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonBoolean} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonBoolean}
     */
    public JsonBoolean getAsJsonBoolean(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return ((JsonValue) element).isBoolean() ? (JsonBoolean) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonNull} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonNull}
     */
    public JsonNull getAsJsonNull(int index) {
        JsonElement element = get(index);

        if (element.isValue()) {
            return element.isNull() ? (JsonNull) element : null;
        } else {
            return null;
        }
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonObject} at the specified position in this list,
     *          or {@code null} if the element is not a object
     */
    public JsonObject getAsObject(int index) {
        JsonElement element = get(index);

        return element.isObject() ? (JsonObject) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonArray} at the specified position in this list,
     *          or {@code null} if the element is not an array
     */
    public JsonArray getAsArray(int index) {
        JsonElement element = get(index);

        return element.isArray() ? (JsonArray) element : null;
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

    /**
     * @return an iterator over the elements in this list in proper sequence
     * @see ArrayList#iterator()
     */
    @Override
    public Iterator<JsonElement> iterator() {
        return delegate.iterator();
    }
}