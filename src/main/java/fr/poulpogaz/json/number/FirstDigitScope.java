package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

/**
 * A class that verify syntax while
 * parsing a number
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class FirstDigitScope extends JsonNumberScope {

    @Override
    public void newDigit(char digit) throws JsonException {
        if (state == STATE_START && digit == '0') {
            state = STATE_DO_NOT_EXPECT_DIGIT;

        } else if (state == STATE_DO_NOT_EXPECT_DIGIT) {
            throw new JsonException();

        } else {
            state = STATE_EXPECT_DIGIT_OR_CLOSE;
        }
    }

    @Override
    public void newHyphen() throws JsonException {
        if (state != STATE_START) {
            throw new JsonException();
        } else {
            state = STATE_EXPECT_DIGIT; // Require digit
        }
    }

    @Override
    public void newPlus() throws JsonException {
        throw new JsonException();
    }

    @Override
    public JsonNumberScope newExponent() throws JsonException {
        if (state != STATE_START && state != STATE_EXPECT_DIGIT) {
            return new ExponentScope();
        } else {
            throw new JsonException();
        }
    }

    @Override
    public JsonNumberScope newPoint() throws JsonException {
        if (state != STATE_START && state != STATE_EXPECT_DIGIT) {
            return new FractionScope();
        } else {
            throw new JsonException();
        }
    }

    @Override
    public void close() throws JsonException {
        if (state == STATE_START || state == STATE_EXPECT_DIGIT) {
            throw new JsonException();
        }
    }

    @Override
    public boolean isFirstDigitScope() {
        return true;
    }
}