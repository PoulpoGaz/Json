package fr.poulpogaz.json.context;

import fr.poulpogaz.json.JsonException;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class ObjectReadContext extends JsonReadContext {

    public ObjectReadContext(JsonReadContext parent) {
        super(parent, STATE_EMPTY);
    }

    @Override
    public JsonReadContext close() throws JsonException {
        if (state == STATE_AFTER_VALUE || state == STATE_EMPTY) {
            return parent;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newKey() throws JsonException {
        if (state == STATE_EXPECT_KEY || state == STATE_EMPTY) {
            state = STATE_EXPECT_COLON;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public boolean mayNeedKey() {
        return state == STATE_EXPECT_KEY || state == STATE_EMPTY;
    }

    @Override
    public void newValue() throws JsonException {
        if (state == STATE_EXPECT_VALUE) {
            state = STATE_AFTER_VALUE;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newComma() throws JsonException {
        if (state == STATE_AFTER_VALUE) {
            state = STATE_EXPECT_KEY;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newColon() throws JsonException {
        if (state == STATE_EXPECT_COLON) {
            state = STATE_EXPECT_VALUE;
        } else {
            throw new JsonException("Syntax error");
        }
    }
}