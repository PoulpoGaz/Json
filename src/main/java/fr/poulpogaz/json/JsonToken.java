package fr.poulpogaz.json;

/**
 * Tokens elements inside a json reader or writer
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public enum JsonToken {

    /** A token representing the beginning of an object **/
    BEGIN_OBJECT_TOKEN(false, false),

    /** A token representing the end of an object **/
    END_OBJECT_TOKEN(false, false),

    /** A token representing the beginning of an array **/
    BEGIN_ARRAY_TOKEN(false, false),

    /** A token representing the end of an array **/
    END_ARRAY_TOKEN(false, false),

    /** A token representing a key **/
    KEY_TOKEN(false, false),

    /** A token representing a string value **/
    STRING_TOKEN(false, true),

    /** A token representing an int value **/
    INT_TOKEN(true, true),

    /** A token representing a long value **/
    LONG_TOKEN(true, true),

    /** A token representing a big integer value **/
    BIG_INTEGER_TOKEN(true, true),

    /** A token representing a decimal value **/
    DECIMAL_TOKEN(true, true),
    // no float or double token because
    // it's very difficult to parse string
    // to float/double and keep the precision
    // and avoid NaN or Infinity values

    /** A token representing a null value **/
    NULL_TOKEN(false, true),

    /** A token representing a boolean value **/
    BOOLEAN_TOKEN(false, true),

    /** A token representing that the end of the stream has been reached **/
    END_TOKEN(false, false);

    /** {@code true} if this token is a number **/
    private final boolean isNumber;

    /** {@code true} if this token is a value **/
    private final boolean isValue;

    /**
     * Constructs a new {@code JsonToken}
     *
     * @param isNumber {@code true} if this token is a number
     * @param isValue {@code true} if this token is a value
     */
    JsonToken(boolean isNumber, boolean isValue) {
        this.isNumber = isNumber;
        this.isValue = isValue;
    }

    /**
     * @return {@link #isNumber}
     */
    public boolean isNumber() {
        return isNumber;
    }

    /**
     * @return {@link #isValue}
     */
    public boolean isValue() {
        return isValue;
    }
}