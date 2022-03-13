package fr.poulpogaz.json.tree.value;

import fr.poulpogaz.json.tree.JsonValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

/**
 * A class that represent a json string
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public class JsonString extends JsonValue {

    /** The value of this {@code JsonString} **/
    private final String value;

    /**
     * Constructs a new {@code JsonString} with an empty {@link String}
     */
    public JsonString() {
        value = "";
    }

    /**
     * Constructs a new {@code JsonString} with te specified {@link String}
     * @param value the value of this {@code JsonString}
     */
    public JsonString(String value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * @return true as this class represents a json string
     */
    @Override
    public boolean isString() {
        return true;
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@code byte}
     *
     * @return this value as a {@code byte} if possible
     * @throws NumberFormatException if the string does not
     *         contain a parsable {@code byte}.
     */
    @Override
    public byte getAsByte() {
        return Byte.parseByte(value);
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@code short}
     *
     * @return this value as a {@code short} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@code short}.
     */
    @Override
    public short getAsShort() throws NumberFormatException {
        return Short.parseShort(value);
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@code int}
     *
     * @return this value as a {@code int} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@code int}.
     */
    @Override
    public int getAsInt() throws NumberFormatException {
        return Integer.parseInt(value);
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@code long}
     *
     * @return this value as a {@code long} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@code long}.
     */
    @Override
    public long getAsLong() throws NumberFormatException {
        return Long.parseLong(value);
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@link BigInteger}
     *
     * @return this value as a {@link BigInteger} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@link BigInteger}.
     */
    @Override
    public BigInteger getAsBigInteger() throws NumberFormatException {
        return getAsBigDecimal().toBigInteger();
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@code float}
     *
     * @return this value as a {@code float} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@code float}.
     */
    @Override
    public float getAsFloat() throws NumberFormatException {
        return Float.parseFloat(value);
    }
    /**
     * Tries to parse this {@code JsonString} as
     * {@code double}
     *
     * @return this value as a {@code double} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@code double}.
     */

    @Override
    public double getAsDouble() throws NumberFormatException {
        return Double.parseDouble(value);
    }

    /**
     * Tries to parse this {@code JsonString} as
     * {@link BigDecimal}
     *
     * @return this value as a {@link BigDecimal} if possible
     * @throws NumberFormatException If the string does not
     *          contain a parsable {@link BigDecimal}.
     */
    @Override
    public BigDecimal getAsBigDecimal() throws NumberFormatException {
        return new BigDecimal(value);
    }

    /**
     * @return the value of this {@code JsonString}
     */
    @Override
    public String getAsString() {
        return value;
    }

    /**
     * @return {@code true} if the value of the string
     *          is {@code "true"} else {@code false}
     */
    @Override
    public boolean getAsBoolean() {
        return Boolean.parseBoolean(value);
    }

    /**
     * @return an {@link Optional} describing a {@code byte},
     * or an empty {@link Optional} if this json element isn't a {@code byte}
     */
    @Override
    public Optional<Byte> optionalByte() {
        try {
            return Optional.of(getAsByte());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@code short},
     * or an empty {@link Optional} if this json element isn't a {@code short}
     */
    @Override
    public Optional<Short> optionalShort() {
        try {
            return Optional.of(getAsShort());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@code int},
     * or an empty {@link Optional} if this json element isn't a {@code int}
     */
    @Override
    public Optional<Integer> optionalInt() {
        try {
            return Optional.of(getAsInt());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@code long},
     * or an empty {@link Optional} if this json element isn't a {@code long}
     */
    @Override
    public Optional<Long> optionalLong() {
        try {
            return Optional.of(getAsLong());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@link BigInteger},
     * or an empty {@link Optional} if this json element isn't a {@link BigInteger}
     */
    @Override
    public Optional<BigInteger> optionalBigInteger() {
        try {
            return Optional.of(getAsBigInteger());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@code float},
     * or an empty {@link Optional} if this json element isn't a {@code float}
     */
    @Override
    public Optional<Float> optionalFloat() {
        try {
            return Optional.of(getAsFloat());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@code double},
     * or an empty {@link Optional} if this json element isn't a {@code double}
     */
    @Override
    public Optional<Double> optionalDouble() {
        try {
            return Optional.of(getAsDouble());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    /**
     * @return an {@link Optional} describing a {@link BigDecimal},
     * or an empty {@link Optional} if this json element isn't a {@link BigDecimal}
     */
    @Override
    public Optional<BigDecimal> optionalBigDecimal() {
        try {
            return Optional.of(getAsBigDecimal());
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
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
        return Optional.of(getAsBoolean());
    }
}