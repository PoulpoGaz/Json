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
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isNumber() ? (JsonNumber) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonString} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonString}
     */
    public JsonString getAsJsonString(int index) {
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isString() ? (JsonString) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonBoolean} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonBoolean}
     */
    public JsonBoolean getAsJsonBoolean(int index) {
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isBoolean() ? (JsonBoolean) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonNull} at the specified position in this list,
     *          or {@code null} if the element is not a value or if it is
     *          not a {@link JsonNull}
     */
    public JsonNull getAsJsonNull(int index) {
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isNull() ? (JsonNull) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonObject} at the specified position in this list,
     *          or {@code null} if the element is not an object
     */
    public JsonObject getAsObject(int index) {
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isObject() ? (JsonObject) element : null;
    }

    /**
     * @param index index of the element to return
     * @return the {@link JsonArray} at the specified position in this list,
     *          or {@code null} if the element is not an array
     */
    public JsonArray getAsArray(int index) {
        if (index < 0 || index >= delegate.size()) {
            return null;
        }

        JsonElement element = get(index);

        return element.isArray() ? (JsonArray) element : null;
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonNumber} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonNumber> getOptionalJsonNumber(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element.isNumber()) {
            return Optional.of((JsonNumber) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonString} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonString> getOptionalJsonString(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element != null && element.isString()) {
            return Optional.of((JsonString) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonBoolean} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonBoolean> getOptionalJsonBoolean(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element != null && element.isBoolean()) {
            return Optional.of((JsonBoolean) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonNull} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonNull> getOptionalJsonNull(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element != null && element.isNull()) {
            return Optional.of((JsonNull) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonObject} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonObject> getOptionalJsonObject(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element != null && element.isObject()) {
            return Optional.of((JsonObject) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing {@link JsonArray} at the specified position
     * in this list, or an empty {@link Optional} if the index is out of range, or if the value
     * isn't a {@link JsonValue}
     */
    public Optional<JsonArray> getOptionalJsonArray(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement element = get(index);

        if (element != null && element.isArray()) {
            return Optional.of((JsonArray) element);
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code byte} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code byte}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public byte getAsByte(int index) {
        return get(index).getAsByte();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code short} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code short}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public short getAsShort(int index) {
        return get(index).getAsShort();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code int} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code int}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public int getAsInt(int index) {
        return get(index).getAsInt();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code long} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code long}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public long getAsLong(int index) {
        return get(index).getAsLong();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@link BigInteger} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link BigInteger}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public BigInteger getAsBigInteger(int index) {
        return get(index).getAsBigInteger();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code float} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code float}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public float getAsFloat(int index) {
        return get(index).getAsFloat();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code double} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code double}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public double getAsDouble(int index) {
        return get(index).getAsDouble();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@link BigDecimal} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link BigDecimal}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public BigDecimal getAsBigDecimal(int index) {
        return get(index).getAsBigDecimal();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@link String} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@link String}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public String getAsString(int index) {
        return get(index).getAsString();
    }

    /**
     * @param index index of the element
     * @return the value at the specified index as {@code boolean} if possible
     * @throws IllegalStateException the value isn't a {@link JsonValue}
     * @throws NumberFormatException the value can't be parsed to a {@code boolean}
     * @throws IndexOutOfBoundsException the index is out of range
     */
    public boolean getAsBoolean(int index) {
        return get(index).getAsBoolean();
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code byte}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code byte}
     */
    public Optional<Byte> getOptionalByte(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalByte();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code short}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code short}
     */
    public Optional<Short> getOptionalShort(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalShort();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code int}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code int}
     */
    public Optional<Integer> getOptionalInt(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalInt();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code long}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code long}
     */
    public Optional<Long> getOptionalLong(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalLong();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@link BigInteger}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@link BigInteger}
     */
    public Optional<BigInteger> getOptionalBigInteger(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalBigInteger();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code float}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code float}
     */
    public Optional<Float> getOptionalFloat(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalFloat();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code double}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code double}
     */
    public Optional<Double> getOptionalDouble(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalDouble();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@link BigDecimal}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@link BigDecimal}
     */
    public Optional<BigDecimal> getOptionalBigDecimal(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalBigDecimal();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@link String}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@link String}
     */
    public Optional<String> getOptionalString(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

        if (e != null) {
            return e.optionalString();
        } else {
            return Optional.empty();
        }
    }

    /**
     * @param index index of the element
     * @return an {@link Optional} describing the value at the specified index as {@code boolean}
     * or an empty {@link Optional} if the index is out of range or if the json element
     * is not a {@code boolean}
     */
    public Optional<Boolean> getOptionalBoolean(int index) {
        if (index < 0 || index >= delegate.size()) {
            return Optional.empty();
        }

        JsonElement e = get(index);

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
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonArray}
     */
    @Override
    public boolean isArray() {
        return true;
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