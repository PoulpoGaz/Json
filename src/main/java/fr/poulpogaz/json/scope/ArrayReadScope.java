package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A class for checking syntax in an
 * array while reading a json stream
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class ArrayReadScope extends JsonReadScope {

    public ArrayReadScope(JsonReadScope parent) {
        super(parent);
    }

    @Override
    public JsonReadScope close() throws JsonException {
        if (state == STATE_EMPTY || state == STATE_AFTER_VALUE) {
            return parent;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newKey() throws JsonException {
        throw new JsonException("Syntax error");
    }

    @Override
    public boolean mayNeedKey() {
        return false;
    }

    @Override
    public void newValue() throws JsonException {
        if (state == STATE_EMPTY || state == STATE_EXPECT_VALUE) {
            state = STATE_AFTER_VALUE;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newComma() throws JsonException {
        if (state == STATE_AFTER_VALUE) {
            state = STATE_EXPECT_VALUE;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newColon() throws JsonException {
        throw new JsonException("Syntax error, ':' in array");
    }
}