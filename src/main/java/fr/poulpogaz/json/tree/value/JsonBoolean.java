package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonBoolean extends JsonValue {

    public static final JsonBoolean TRUE = new JsonBoolean(true);

    public static final JsonBoolean FALSE = new JsonBoolean(false);

    public static JsonBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    private final boolean value;

    private JsonBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public byte getAsByte() {
        return (byte) (value ? 1 : 0);
    }

    @Override
    public short getAsShort() {
        return (short) (value ? 1 : 0);
    }

    @Override
    public int getAsInt() {
        return value ? 1 : 0;
    }

    @Override
    public long getAsLong() {
        return value ? 1L : 0L;
    }

    @Override
    public BigInteger getAsBigInteger() {
        return value ? BigInteger.ONE : BigInteger.ZERO;
    }

    @Override
    public float getAsFloat() {
        return value ? 1f : 0f;
    }

    @Override
    public double getAsDouble() {
        return value ? 1d : 0d;
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        return value ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    @Override
    public String getAsString() {
        return String.valueOf(value);
    }

    @Override
    public boolean getAsBoolean() {
        return value;
    }

    @Override
    public boolean isBoolean() {
        return true;
    }
}