package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

/**
 * A class that verify syntax while
 * parsing the exponent part in a
 * number
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class ExponentScope extends JsonNumberScope {

    @Override
    public void newHyphen() throws JsonException {
        if (state == STATE_START) {
            state = STATE_EXPECT_DIGIT;
        } else {
            throw new JsonException();
        }
    }

    @Override
    public void newPlus() throws JsonException {
        if (state == STATE_START) {
            state = STATE_EXPECT_DIGIT;
        } else {
            throw new JsonException();
        }
    }

    @Override
    public void newDigit(char digit) throws JsonException {
        state = STATE_EXPECT_DIGIT_OR_CLOSE;
    }

    @Override
    public JsonNumberScope newExponent() throws JsonException {
        throw new JsonException();
    }

    @Override
    public JsonNumberScope newPoint() throws JsonException {
        throw new JsonException();
    }

    @Override
    public void close() throws JsonException {
        if (state == STATE_START || state == STATE_EXPECT_DIGIT) {
            throw new JsonException();
        }
    }

    @Override
    public boolean isExponentScope() {
        return true;
    }
}