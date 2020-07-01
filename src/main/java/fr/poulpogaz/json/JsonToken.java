package fr.poulpogaz.json;

public enum JsonToken {

    BEGIN_OBJECT_TOKEN(false, false),
    END_OBJECT_TOKEN(false, false),

    BEGIN_ARRAY_TOKEN(false, false),
    END_ARRAY_TOKEN(false, false),

    KEY_TOKEN(false, false),

    STRING_TOKEN(false, true),

    INT_TOKEN(true, true),
    LONG_TOKEN(true, true),
    FLOAT_TOKEN(true, true),
    BIG_INTEGER_TOKEN(true, true),
    BIG_DECIMAL_TOKEN(true, true),

    NULL_TOKEN(false, true),
    BOOLEAN_TOKEN(false, true),

    END_TOKEN(false, false);

    private final boolean isNumber;
    private final boolean isValue;

    JsonToken(boolean isNumber, boolean isValue) {
        this.isNumber = isNumber;
        this.isValue = isValue;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public boolean isValue() {
        return isValue;
    }
}