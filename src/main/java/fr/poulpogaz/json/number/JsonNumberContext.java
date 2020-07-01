package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

public abstract class JsonNumberContext {

    public static final int STATE_START = 0;
    public static final int STATE_DO_NOT_EXPECT_DIGIT = 1;
    public static final int STATE_EXPECT_DIGIT = 2;
    public static final int STATE_EXPECT_DIGIT_OR_CLOSE = 3;

    protected int state;

    public JsonNumberContext() {
        this.state = STATE_START;
    }

    public abstract void newDigit(char digit) throws JsonException;

    // hyphen = -
    public abstract void newHyphen() throws JsonException;

    public abstract void newPlus() throws JsonException;

    public abstract JsonNumberContext newExponent() throws JsonException;

    public abstract JsonNumberContext newPoint() throws JsonException;

    public abstract void close() throws JsonException;

    public boolean isFirstDigitContext() {
        return false;
    }

    public boolean isFractionContext() {
        return false;
    }

    public boolean isExponentContext() {
        return false;
    }
}