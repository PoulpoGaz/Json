package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

public class ExponentContext extends JsonNumberContext {

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
    public JsonNumberContext newExponent() throws JsonException {
        throw new JsonException();
    }

    @Override
    public JsonNumberContext newPoint() throws JsonException {
        throw new JsonException();
    }

    @Override
    public void close() throws JsonException {
        if (state == STATE_START || state == STATE_EXPECT_DIGIT) {
            throw new JsonException();
        }
    }

    @Override
    public boolean isExponentContext() {
        return true;
    }
}