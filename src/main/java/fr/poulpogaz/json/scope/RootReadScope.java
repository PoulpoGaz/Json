package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A class for checking syntax in the
 * first scope while reading a json
 * stream
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class RootReadScope extends JsonReadScope {

    public RootReadScope() {
        super(null);
    }

    @Override
    public JsonReadScope close() throws JsonException {
        if (state == STATE_EMPTY || state == STATE_END) {
            return null;
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
        if (state == STATE_EMPTY) {
            state = STATE_END;
        } else {
            throw new JsonException("Syntax error");
        }
    }

    @Override
    public void newComma() throws JsonException {
        throw new JsonException("Syntax error: ','");
    }

    @Override
    public void newColon() throws JsonException {
        throw new JsonException("Syntax error: ':");
    }
}