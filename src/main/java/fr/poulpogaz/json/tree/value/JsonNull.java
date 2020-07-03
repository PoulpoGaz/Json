package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public final class JsonNull extends JsonValue {

    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {

    }

    @Override
    public byte getAsByte() {
        throw new UnsupportedOperationException();
    }

    @Override
    public short getAsShort() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getAsInt() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long getAsLong() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigInteger getAsBigInteger() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getAsFloat() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getAsDouble() {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getAsString() {
        return "null";
    }

    @Override
    public boolean getAsBoolean() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isNull() {
        return true;
    }
}