package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

/**
 * A class that verify syntax while
 * parsing the fractional part in a
 * number
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class FractionScope extends JsonNumberScope {

    @Override
    public void newDigit(char digit) throws JsonException {
        state = STATE_EXPECT_DIGIT_OR_CLOSE;
    }

    @Override
    public void newHyphen() throws JsonException {
        throw new JsonException();
    }

    @Override
    public void newPlus() throws JsonException {
        throw new JsonException();
    }

    @Override
    public JsonNumberScope newExponent() throws JsonException {
        if (state != STATE_START) {
            return new ExponentScope();
        } else {
            throw new JsonException();
        }
    }

    @Override
    public JsonNumberScope newPoint() throws JsonException {
        throw new JsonException();
    }

    @Override
    public void close() throws JsonException {
        if (state == STATE_START) {
            throw new JsonException();
        }
    }

    @Override
    public boolean isFractionScope() {
        return true;
    }
}