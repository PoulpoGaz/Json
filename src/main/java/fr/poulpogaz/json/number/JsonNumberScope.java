package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class JsonNumberScope {

    public static final int STATE_START = 0;
    public static final int STATE_DO_NOT_EXPECT_DIGIT = 1;
    public static final int STATE_EXPECT_DIGIT = 2;
    public static final int STATE_EXPECT_DIGIT_OR_CLOSE = 3;

    protected int state;

    public JsonNumberScope() {
        this.state = STATE_START;
    }

    public abstract void newDigit(char digit) throws JsonException;

    // hyphen = -
    public abstract void newHyphen() throws JsonException;

    public abstract void newPlus() throws JsonException;

    public abstract JsonNumberScope newExponent() throws JsonException;

    public abstract JsonNumberScope newPoint() throws JsonException;

    public abstract void close() throws JsonException;

    public boolean isFirstDigitScope() {
        return false;
    }

    public boolean isFractionScope() {
        return false;
    }

    public boolean isExponentScope() {
        return false;
    }
}