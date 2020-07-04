package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A class that represent a json boolean
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonBoolean extends JsonValue {

    /** A constant representing the value true **/
    public static final JsonBoolean TRUE = new JsonBoolean(true);

    /** A constant representing the value false **/
    public static final JsonBoolean FALSE = new JsonBoolean(false);

    /**
     * Returns a {@code JsonBoolean} based of the
     * specified boolean
     *
     * @param value the value of the {@code JsonBoolean}
     * @return {@link #TRUE} if value is true else
     *          {@link #FALSE}
     */
    public static JsonBoolean of(boolean value) {
        return value ? TRUE : FALSE;
    }

    /** The value of this {@code JsonBoolean} **/
    private final boolean value;

    /**
     * Constructs a new JsonBoolean
     *
     * @param value the value of the {@code JsonBoolean}
     */
    private JsonBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code byte}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public byte getAsByte() {
        return (byte) (value ? 1 : 0);
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code short}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public short getAsShort() {
        return (short) (value ? 1 : 0);
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code int}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public int getAsInt() {
        return value ? 1 : 0;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code long}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public long getAsLong() {
        return value ? 1L : 0L;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@link BigInteger}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public BigInteger getAsBigInteger() {
        return value ? BigInteger.ONE : BigInteger.ZERO;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code float}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public float getAsFloat() {
        return value ? 1f : 0f;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code double}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public double getAsDouble() {
        return value ? 1d : 0d;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@link BigDecimal}
     *
     * @return 1 if value is true else 0
     */
    @Override
    public BigDecimal getAsBigDecimal() {
        return value ? BigDecimal.ONE : BigDecimal.ZERO;
    }

    /**
     * Converts this {@code JsonBoolean} to a {@code String}
     *
     * @return {@code "true"} if value is true else {@code "false"}
     */
    @Override
    public String getAsString() {
        return String.valueOf(value);
    }

    /**
     * @return the value of this {@code JsonBoolean}
     */
    @Override
    public boolean getAsBoolean() {
        return value;
    }

    /**
     * @return true as this class represents a json boolean
     */
    @Override
    public boolean isBoolean() {
        return true;
    }
}