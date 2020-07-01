package fr.poulpogaz.json.context;

import fr.poulpogaz.json.JsonException;

public class RootReadContext extends JsonReadContext {

    public RootReadContext() {
        super(null, STATE_EMPTY);
    }

    @Override
    public JsonReadContext close() throws JsonException {
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