package fr.poulpogaz.json.tree;

/**
 * Base class for the tree api.
 * This class represents a json data type (object,
 * array or value)
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public interface JsonElement {

    /**
     * @return true if this {@code JsonElement} is a {@link JsonObject}
     */
    boolean isObject();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonArray}
     */
    boolean isArray();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonValue}
     */
    boolean isValue();
}