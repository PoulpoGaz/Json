package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class ArrayWriteScope extends JsonWriteScope {

    public ArrayWriteScope(JsonWriteScope parent) {
        super(parent, STATE_EMPTY);
    }

    @Override
    public void newKey() throws JsonException {
        throw new JsonException("Array doesn't have keys");
    }

    @Override
    public void newValue() throws JsonException {
        if (state != STATE_EMPTY && state != STATE_AFTER_VALUE) {
            throw new JsonException("Nesting problem");
        } else if (state == STATE_EMPTY) {
            state = STATE_AFTER_VALUE;
        }
    }

    @Override
    public void newField() throws JsonException {
        throw new JsonException("Cannot add field to an array");
    }

    @Override
    public JsonWriteScope close() throws JsonException {
        if (state == STATE_AFTER_VALUE || state == STATE_EMPTY) {
            state = STATE_END;
            return parent;
        } else {
            throw new JsonException("Nesting problem");
        }
    }

    @Override
    public boolean isArray() {
        return true;
    }
}