package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/**
 * A class that represent a json number
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonNumber extends JsonValue {

    /** The value of this {@code JsonNumber}**/
    private final Number number;

    /**
     * Constructs a new {@code JsonNumber} with value 0
     */
    public JsonNumber() {
        number = 0;
    }

    /**
     * Constructs a new {@code JsonNumber} with the
     * specified value, which may not be {@code null}
     */
    public JsonNumber(Number number) {
        this.number = Objects.requireNonNull(number);
    }

    /**
     * @return true if this number is a {@code byte}
     */
    public boolean isByte() {
        return number instanceof Byte;
    }

    /**
     * @return true if this number is a {@code short}
     */
    public boolean isShort() {
        return number instanceof Short;
    }

    /**
     * @return true if this number is an {@code in}
     */
    public boolean isInt() {
        return number instanceof Integer;
    }

    /**
     * @return true if this number is a {@code long}
     */
    public boolean isLong() {
        return number instanceof Long;
    }

    /**
     * @return true if this number is a {@code float}
     */
    public boolean isFloat() {
        return number instanceof Float;
    }

    /**
     * @return true if this number is a {@code double}
     */
    public boolean isDouble() {
        return number instanceof Double;
    }

    /**
     * @return true if this number is a {@link BigInteger}
     */
    public boolean isBigInteger() {
        return number instanceof BigInteger;
    }

    /**
     * @return true if this number is a {@link BigDecimal}
     */
    public boolean isBigDecimal() {
        return number instanceof BigDecimal;
    }

    /**
     * @return this value as a {@code byte}
     */
    public byte getAsByte() {
        return number.byteValue();
    }

    /**
     * @return this value as a {@code short}
     */
    public short getAsShort() {
        return number.shortValue();
    }

    /**
     * @return this value as a {@code int}
     */
    public int getAsInt() {
        return number.intValue();
    }

    /**
     * @return this value as a {@code long}
     */
    public long getAsLong() {
        return number.longValue();
    }

    /**
     * @return this value as a {@code float}
     */
    public float getAsFloat() {
        return number.floatValue();
    }

    /**
     * @return this value as a {@code double}
     */
    public double getAsDouble() {
        return number.doubleValue();
    }

    /**
     * @return this value as a {@link BigInteger}
     */
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

    /**
     * @return this value as a {@link BigDecimal}
     */
    public BigDecimal getAsBigDecimal() {
        return new BigDecimal(number.toString());
    }

    /**
     * @return the value of this {@code JsonNumber}
     */
    public Number getNumber() {
        return number;
    }

    /**
     * @return this value as a {@link String}
     */
    @Override
    public String getAsString() {
        return number.toString();
    }

    /**
     * @return {@code true} if this value is 1 else {@code false}
     */
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

    /**
     * @return true as this class represents a json number
     */
    @Override
    public boolean isNumber() {
        return true;
    }
}