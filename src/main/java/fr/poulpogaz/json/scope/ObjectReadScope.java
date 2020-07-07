package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A class for checking syntax in an
 * object while reading a json stream
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class ObjectReadScope extends JsonReadScope {

    public ObjectReadScope(JsonReadScope parent) {
        super(parent);
    }

    @Override
    public JsonReadScope close() throws JsonException {
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