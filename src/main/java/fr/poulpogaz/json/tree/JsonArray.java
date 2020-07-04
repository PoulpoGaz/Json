package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A class that represents a json array.
 *
 * @implNote
 * This class doesn't override the {@link ArrayList}
 * to avoid {@code null}
 *
 * @author PoulpoGaz
 * @version 1.0
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
            return ((JsonValue) element).isNull() ? (JsonNull) element : null;
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
     * @return false as this class represents an arrat and not an object
     */
    @Override
    public boolean isObject() {
        return false;
    }

    /**
     * @return true as this class represents an array
     */
    @Override
    public boolean isArray() {
        return true;
    }

    /**
     * @return false as this class represents an array and not a value
     */
    @Override
    public boolean isValue() {
        return false;
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