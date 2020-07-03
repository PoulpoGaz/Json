package fr.poulpogaz.json.tree;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class JsonValue implements JsonElement {

    public abstract byte getAsByte();

    public abstract short getAsShort();

    public abstract int getAsInt();

    public abstract long getAsLong();

    public abstract BigInteger getAsBigInteger();

    public abstract float getAsFloat();

    public abstract double getAsDouble();

    public abstract BigDecimal getAsBigDecimal();

    public abstract String getAsString();

    public abstract boolean getAsBoolean();

    public boolean isNumber() {
        return false;
    }

    public boolean isString() {
        return false;
    }

    public boolean isBoolean() {
        return false;
    }

    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isObject() {
        return false;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isValue() {
        return true;
    }
}