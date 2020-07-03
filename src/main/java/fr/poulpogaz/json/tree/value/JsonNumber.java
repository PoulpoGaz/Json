package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class JsonNumber extends JsonValue {

    private final Number number;

    public JsonNumber() {
        number = 0;
    }

    public JsonNumber(Number number) {
        this.number = Objects.requireNonNull(number);
    }

    public boolean isByte() {
        return number instanceof Byte;
    }

    public boolean isShort() {
        return number instanceof Short;
    }

    public boolean isInt() {
        return number instanceof Integer;
    }

    public boolean isLong() {
        return number instanceof Long;
    }

    public boolean isFloat() {
        return number instanceof Float;
    }

    public boolean isDouble() {
        return number instanceof Double;
    }

    public boolean isBigInteger() {
        return number instanceof BigInteger;
    }

    public boolean isBigDecimal() {
        return number instanceof BigDecimal;
    }

    public byte getAsByte() {
        return number.byteValue();
    }

    public short getAsShort() {
        return number.shortValue();
    }

    public int getAsInt() {
        return number.intValue();
    }

    public long getAsLong() {
        return number.longValue();
    }

    public float getAsFloat() {
        return number.floatValue();
    }

    public double getAsDouble() {
        return number.doubleValue();
    }

    public BigInteger getAsBigInteger() {
        if (isLong() || isInt() || isShort() || isByte()) {
            return BigInteger.valueOf(getAsLong());
        } else if (isBigInteger()) {
            return (BigInteger) number;
        } if (isDouble() || isFloat()) {
            return getAsBigDecimal().toBigInteger();
        } else {
            return ((BigDecimal) number).toBigInteger();
        }
    }

    public BigDecimal getAsBigDecimal() {
        return new BigDecimal(number.toString());
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public String getAsString() {
        return number.toString();
    }

    @Override
    public boolean getAsBoolean() {
        if (isBigInteger()) {
            return getAsBigInteger().equals(BigInteger.ONE);
        } else if (isBigDecimal()) {
            return getAsBigDecimal().equals(BigDecimal.ONE);
        } else {
            return number.doubleValue() == 1; // use double value because default java number can fit in double
        }
    }

    @Override
    public boolean isNumber() {
        return true;
    }
}