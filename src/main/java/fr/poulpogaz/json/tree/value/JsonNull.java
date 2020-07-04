package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A class that represent a json null
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public final class JsonNull extends JsonValue {

    /** A constant representing the null value**/
    public static final JsonNull INSTANCE = new JsonNull();

    /**
     * Constructs a new {@code JsonNull}
     */
    private JsonNull() {

    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code byte}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code byte}
     */
    @Override
    public byte getAsByte() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a byte");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code short}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code short}
     */
    @Override
    public short getAsShort() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a short");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code int}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code int}
     */
    @Override
    public int getAsInt() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to an int");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code long}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code long}
     */
    @Override
    public long getAsLong() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a long");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@link BigInteger}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@link BigInteger}
     */
    @Override
    public BigInteger getAsBigInteger() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a BigInteger");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code float}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code float}
     */
    @Override
    public float getAsFloat() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a float");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code double}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code double}
     */
    @Override
    public double getAsDouble() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a double");
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@link BigDecimal}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@link BigDecimal}
     */
    @Override
    public BigDecimal getAsBigDecimal() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a BigDecimal");
    }

    /**
     * Returns this value as string
     *
     * @return {@code "null"}
     */
    @Override
    public String getAsString() {
        return "null";
    }

    /**
     * throws an {@link UnsupportedOperationException} as a
     * null value cannot be converted to {@code boolean}
     * @return nothing
     * @throws UnsupportedOperationException as a {@code null}
     *          cannot be converted to {@code boolean}
     */
    @Override
    public boolean getAsBoolean() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot convert a null value to a boolean");
    }

    /**
     * @return true as this class represents a json null
     */
    @Override
    public boolean isNull() {
        return true;
    }
}