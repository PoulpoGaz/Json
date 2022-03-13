package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * A class that represents a json value
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public abstract class JsonValue implements JsonElement {

    /**
     * @return true if this {@code JsonElement} is a {@link JsonObject}
     */
    @Override
    public boolean isObject() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonArray}
     */
    @Override
    public boolean isArray() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonValue}
     */
    @Override
    public boolean isValue() {
        return true;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNumber}
     */
    @Override
    public boolean isNumber() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonString}
     */
    @Override
    public boolean isString() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonBoolean}
     */
    @Override
    public boolean isBoolean() {
        return false;
    }

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNull}
     */
    @Override
    public boolean isNull() {
        return false;
    }

    /**
     * @return this value as {@code byte} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code byte}
     */
    @Override
    public abstract byte getAsByte();

    /**
     * @return this value as {@code short} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code short}
     */
    @Override
    public abstract short getAsShort();

    /**
     * @return this value as {@code int} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code int}
     */
    @Override
    public abstract int getAsInt();

    /**
     * @return this value as {@code long} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code long}
     */
    @Override
    public abstract long getAsLong();

    /**
     * @return this value as {@link BigInteger} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigInteger}
     */
    @Override
    public abstract BigInteger getAsBigInteger();

    /**
     * @return this value as {@code float} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code float}
     */
    @Override
    public abstract float getAsFloat();

    /**
     * @return this value as {@code double} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code double}
     */
    @Override
    public abstract double getAsDouble();

    /**
     * @return this value as {@link BigDecimal} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigDecimal}
     */
    @Override
    public abstract BigDecimal getAsBigDecimal();

    /**
     * @return this value as {@link String} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     */
    @Override
    public abstract String getAsString();

    /**
     * @return this value as {@code boolean} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws UnsupportedOperationException if it can't be parsed to a {@code boolean}
     */
    @Override
    public abstract boolean getAsBoolean();

    /**
     * @return an {@link Optional} describing a {@code byte},
     * or an empty {@link Optional} if this json element isn't a {@code byte}
     */
    public abstract Optional<Byte> optionalByte();

    /**
     * @return an {@link Optional} describing a {@code short},
     * or an empty {@link Optional} if this json element isn't a {@code short}
     */
    public abstract Optional<Short> optionalShort();

    /**
     * @return an {@link Optional} describing a {@code int},
     * or an empty {@link Optional} if this json element isn't a {@code int}
     */
    public abstract Optional<Integer> optionalInt();

    /**
     * @return an {@link Optional} describing a {@code long},
     * or an empty {@link Optional} if this json element isn't a {@code long}
     */
    public abstract Optional<Long> optionalLong();

    /**
     * @return an {@link Optional} describing a {@link BigInteger},
     * or an empty {@link Optional} if this json element isn't a {@link BigInteger}
     */
    public abstract Optional<BigInteger> optionalBigInteger();

    /**
     * @return an {@link Optional} describing a {@code float},
     * or an empty {@link Optional} if this json element isn't a {@code float}
     */
    public abstract Optional<Float> optionalFloat();

    /**
     * @return an {@link Optional} describing a {@code double},
     * or an empty {@link Optional} if this json element isn't a {@code double}
     */
    public abstract Optional<Double> optionalDouble();

    /**
     * @return an {@link Optional} describing a {@link BigDecimal},
     * or an empty {@link Optional} if this json element isn't a {@link BigDecimal}
     */
    public abstract Optional<BigDecimal> optionalBigDecimal();

    /**
     * @return an {@link Optional} describing a {@link String},
     * or an empty {@link Optional} if this json element isn't a {@link String}
     */
    public abstract Optional<String> optionalString();

    /**
     * @return an {@link Optional} describing a {@code boolean},
     * or an empty {@link Optional} if this json element isn't a {@code boolean}
     */
    public abstract Optional<Boolean> optionalBoolean();
}