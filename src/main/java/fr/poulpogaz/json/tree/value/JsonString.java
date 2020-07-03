package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonString extends JsonValue {

    private final String value;

    public JsonString() {
        value = "";
    }

    public JsonString(String value) {
        this.value = Objects.requireNonNull(value);
    }


    @Override
    public byte getAsByte() {
        return Byte.parseByte(value);
    }

    @Override
    public short getAsShort() {
        return Short.parseShort(value);
    }

    @Override
    public int getAsInt() {
        return Integer.parseInt(value);
    }

    @Override
    public long getAsLong() {
        return Long.parseLong(value);
    }

    @Override
    public BigInteger getAsBigInteger() {
        return getAsBigDecimal().toBigInteger();
    }

    @Override
    public float getAsFloat() {
        return Float.parseFloat(value);
    }

    @Override
    public double getAsDouble() {
        return Double.parseDouble(value);
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        return new BigDecimal(value);
    }

    @Override
    public String getAsString() {
        return value;
    }

    @Override
    public boolean getAsBoolean() {
        return Boolean.parseBoolean(value);
    }

    @Override
    public boolean isString() {
        return true;
    }
}