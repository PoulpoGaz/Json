package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A class for checking syntax in an
 * object while writing
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class ObjectWriteScope extends JsonWriteScope {

    public ObjectWriteScope(JsonWriteScope parent) {
        super(parent, STATE_EMPTY);
    }

    @Override
    public void newKey() throws JsonException {
        if (state == STATE_EMPTY) { // No key or value was added -> OK
            state = STATE_EXPECT_VALUE;

        } else if (state == STATE_AFTER_VALUE) {
            state = STATE_EXPECT_VALUE;

        } else if (state == STATE_EXPECT_VALUE) {
            throw new JsonException("Cannot add two key in a row");
        } else {
            throw new JsonException("Nesting problem");
        }
    }

    @Override
    public void newValue() throws JsonException {
        if (state == STATE_EXPECT_VALUE) {
            state = STATE_AFTER_VALUE;
        } else {
            throw new JsonException("A value need a key before it");
        }
    }

    @Override
    public void newField() throws JsonException {
        if (state != STATE_AFTER_VALUE && state != STATE_EMPTY) {
            throw new JsonException("Nesting problem");
        } else if (state == STATE_EMPTY) {
            state = STATE_AFTER_VALUE;
        }
    }

    @Override
    public JsonWriteScope close() throws JsonException {
        if (state == STATE_AFTER_VALUE || state == STATE_EMPTY) {
            state = STATE_END;
            return parent;
        } else if (state == STATE_EXPECT_VALUE) {
            throw new JsonException("Cannot close if a value is expected");
        } else {
            throw new JsonException("Nesting problem");
        }
    }

    @Override
    public boolean isObject() {
        return true;
    }
}