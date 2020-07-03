package fr.poulpogaz.json.tree;

import java.math.BigDecimal;
import java.math.BigInteger;
import fr.poulpogaz.json.tree.value.*;

/**
 * A class that represents a json value
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class JsonValue implements JsonElement {

    /**
     * @return this value as {@code byte} if possible
     */
    public abstract byte getAsByte();

    /**
     * @return this value as {@code short} if possible
     */
    public abstract short getAsShort();

    /**
     * @return this value as {@code int} if possible
     */
    public abstract int getAsInt();

    /**
     * @return this value as {@code long} if possible
     */
    public abstract long getAsLong();

    /**
     * @return this value as {@link BigInteger} if possible
     */
    public abstract BigInteger getAsBigInteger();

    /**
     * @return this value as {@code float} if possible
     */
    public abstract float getAsFloat();

    /**
     * @return this value as {@code double} if possible
     */
    public abstract double getAsDouble();

    /**
     * @return this value as {@link BigDecimal} if possible
     */
    public abstract BigDecimal getAsBigDecimal();

    /**
     * @return this value as {@link String} if possible
     */
    public abstract String getAsString();

    /**
     * @return this value as {@code boolean} if possible
     */
    public abstract boolean getAsBoolean();

    /**
     * @return true if this {@code JsonValue} is a {@link JsonNumber}
     */
    public boolean isNumber() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonString}
     */
    public boolean isString() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonBoolean}
     */
    public boolean isBoolean() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNull}
     */
    public boolean isNull() {
        return false;
    }

    /**
     * @return false as this class represents a value and not an object
     */
    @Override
    public boolean isObject() {
        return false;
    }

    /**
     * @return false as this class represents a value and not an array
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * @return true as this class represents a value
     */
    @Override
    public boolean isValue() {
        return true;
    }
}