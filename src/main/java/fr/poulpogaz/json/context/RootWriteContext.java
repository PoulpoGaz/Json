package fr.poulpogaz.json.context;

import fr.poulpogaz.json.JsonException;

public class RootWriteContext extends JsonWriteContext {

    public RootWriteContext() {
        super(null, STATE_EXPECT_VALUE);
    }

    @Override
    public void newKey() throws JsonException {
        throw new JsonException("Key aren't accepted in root context");
    }

    @Override
    public void newValue() throws JsonException {
        if (state == STATE_EXPECT_VALUE) {
            state = STATE_END;
        } else {
            throw new JsonException("A value was already added");
        }
    }

    @Override
    public void newField() throws JsonException {
        throw new JsonException("Cannot add a field to root");
    }

    @Override
    public JsonWriteContext close() {
        return null;
    }

    @Override
    public boolean isRoot() {
        return true;
    }

    @Override
    public boolean needComma() {
        return false;
    }
}