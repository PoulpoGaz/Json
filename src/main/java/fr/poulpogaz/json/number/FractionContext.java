package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

public class FractionContext extends JsonNumberContext {

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
    public JsonNumberContext newExponent() throws JsonException {
        if (state != STATE_START) {
            return new ExponentContext();
        } else {
            throw new JsonException();
        }
    }

    @Override
    public JsonNumberContext newPoint() throws JsonException {
        throw new JsonException();
    }

    @Override
    public void close() throws JsonException {
        if (state == STATE_START) {
            throw new JsonException();
        }
    }

    @Override
    public boolean isFractionContext() {
        return true;
    }
}