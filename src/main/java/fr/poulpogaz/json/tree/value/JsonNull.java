package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * A class that represent a json null
 *
 * @author PoulpoGaz
 * @version 1.1
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
     * @return true as this class represents a json null
     */
    @Override
    public boolean isNull() {
        return true;
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code byte}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code byte}
     */
    @Override
    public byte getAsByte() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a byte");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code short}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code short}
     */
    @Override
    public short getAsShort() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a short");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code int}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code int}
     */
    @Override
    public int getAsInt() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to an int");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code long}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code long}
     */
    @Override
    public long getAsLong() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a long");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@link BigInteger}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@link BigInteger}
     */
    @Override
    public BigInteger getAsBigInteger() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a BigInteger");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code float}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code float}
     */
    @Override
    public float getAsFloat() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a float");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code double}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code double}
     */
    @Override
    public double getAsDouble() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a double");
    }

    /**
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@link BigDecimal}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@link BigDecimal}
     */
    @Override
    public BigDecimal getAsBigDecimal() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a BigDecimal");
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
     * throws an {@link NumberFormatException} as a
     * null value cannot be converted to {@code boolean}
     * @return nothing
     * @throws NumberFormatException as a {@code null}
     *          cannot be converted to {@code boolean}
     */
    @Override
    public boolean getAsBoolean() throws NumberFormatException {
        throw new NumberFormatException("Cannot convert a null value to a boolean");
    }

    /**
     * @return an {@link Optional} describing a {@code byte},
     * or an empty {@link Optional} if this json element isn't a {@code byte}
     */
    @Override
    public Optional<Byte> optionalByte() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code short},
     * or an empty {@link Optional} if this json element isn't a {@code short}
     */
    @Override
    public Optional<Short> optionalShort() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code int},
     * or an empty {@link Optional} if this json element isn't a {@code int}
     */
    @Override
    public Optional<Integer> optionalInt() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code long},
     * or an empty {@link Optional} if this json element isn't a {@code long}
     */
    @Override
    public Optional<Long> optionalLong() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link BigInteger},
     * or an empty {@link Optional} if this json element isn't a {@link BigInteger}
     */
    @Override
    public Optional<BigInteger> optionalBigInteger() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code float},
     * or an empty {@link Optional} if this json element isn't a {@code float}
     */
    @Override
    public Optional<Float> optionalFloat() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@code double},
     * or an empty {@link Optional} if this json element isn't a {@code double}
     */
    @Override
    public Optional<Double> optionalDouble() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link BigDecimal},
     * or an empty {@link Optional} if this json element isn't a {@link BigDecimal}
     */
    @Override
    public Optional<BigDecimal> optionalBigDecimal() {
        return Optional.empty();
    }

    /**
     * @return an {@link Optional} describing a {@link String},
     * or an empty {@link Optional} if this json element isn't a {@link String}
     */
    @Override
    public Optional<String> optionalString() {
        return Optional.of(getAsString());
    }

    /**
     * @return an {@link Optional} describing a {@code boolean},
     * or an empty {@link Optional} if this json element isn't a {@code boolean}
     */
    @Override
    public Optional<Boolean> optionalBoolean() {
        return Optional.empty();
    }
}