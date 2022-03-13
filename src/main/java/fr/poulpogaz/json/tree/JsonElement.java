package fr.poulpogaz.json.tree;

import fr.poulpogaz.json.tree.value.JsonBoolean;
import fr.poulpogaz.json.tree.value.JsonNull;
import fr.poulpogaz.json.tree.value.JsonNumber;
import fr.poulpogaz.json.tree.value.JsonString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

/**
 * Base class for the tree api.
 * This class represents a json data type (object, array or value)
 *
 * @author PoulpoGaz
 * @version 1.1
 */
public interface JsonElement {

    /**
     * @return true if this {@code JsonElement} is a {@link JsonObject}
     */
    boolean isObject();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonArray}
     */
    boolean isArray();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonValue}
     */
    boolean isValue();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNumber}
     */
    boolean isNumber();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonString}
     */
    boolean isString();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonBoolean}
     */
    boolean isBoolean();

    /**
     * @return true if this {@code JsonElement} is a {@link JsonNull}
     */
    boolean isNull();

    /**
     * @return this value as {@code byte} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code byte}
     */
    byte getAsByte();

    /**
     * @return this value as {@code short} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code short}
     */
    short getAsShort();

    /**
     * @return this value as {@code int} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code int}
     */
    int getAsInt();

    /**
     * @return this value as {@code long} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code long}
     */
    long getAsLong();

    /**
     * @return this value as {@link BigInteger} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigInteger}
     */
    BigInteger getAsBigInteger();

    /**
     * @return this value as {@code float} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code float}
     */
    float getAsFloat();

    /**
     * @return this value as {@code double} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@code double}
     */
    double getAsDouble();

    /**
     * @return this value as {@link BigDecimal} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link BigDecimal}
     */
    BigDecimal getAsBigDecimal();

    /**
     * @return this value as {@link String} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws NumberFormatException if it can't be parsed to a {@link String}
     */
    String getAsString();

    /**
     * @return this value as {@code boolean} if possible
     * @throws IllegalStateException if this {@code JsonElement} isn't a {@link JsonValue}
     * @throws UnsupportedOperationException if it can't be parsed to a {@code boolean}
     */
    boolean getAsBoolean();

    /**
     * @return an {@link Optional} describing a {@code byte},
     * or an empty {@link Optional} if this json element isn't a {@code byte}
     */
    Optional<Byte> optionalByte();

    /**
     * @return an {@link Optional} describing a {@code short},
     * or an empty {@link Optional} if this json element isn't a {@code short}
     */
    Optional<Short> optionalShort();

    /**
     * @return an {@link Optional} describing a {@code int},
     * or an empty {@link Optional} if this json element isn't a {@code int}
     */
    Optional<Integer> optionalInt();

    /**
     * @return an {@link Optional} describing a {@code long},
     * or an empty {@link Optional} if this json element isn't a {@code long}
     */
    Optional<Long> optionalLong();

    /**
     * @return an {@link Optional} describing a {@link BigInteger},
     * or an empty {@link Optional} if this json element isn't a {@link BigInteger}
     */
    Optional<BigInteger> optionalBigInteger();

    /**
     * @return an {@link Optional} describing a {@code float},
     * or an empty {@link Optional} if this json element isn't a {@code float}
     */
    Optional<Float> optionalFloat();

    /**
     * @return an {@link Optional} describing a {@code double},
     * or an empty {@link Optional} if this json element isn't a {@code double}
     */
    Optional<Double> optionalDouble();

    /**
     * @return an {@link Optional} describing a {@link BigDecimal},
     * or an empty {@link Optional} if this json element isn't a {@link BigDecimal}
     */
    Optional<BigDecimal> optionalBigDecimal();

    /**
     * @return an {@link Optional} describing a {@link String},
     * or an empty {@link Optional} if this json element isn't a {@link String}
     */
    Optional<String> optionalString();

    /**
     * @return an {@link Optional} describing a {@code boolean},
     * or an empty {@link Optional} if this json element isn't a {@code boolean}
     */
    Optional<Boolean> optionalBoolean();
}