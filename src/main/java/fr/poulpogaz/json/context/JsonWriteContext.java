package fr.poulpogaz.json.context;

import fr.poulpogaz.json.JsonException;

public abstract class JsonWriteContext {

    public static JsonWriteContext createRootContext() {
        return new RootWriteContext();
    }

    public static final int STATE_EMPTY = 0;
    public static final int STATE_EXPECT_VALUE = 1;
    public static final int STATE_AFTER_VALUE = 2;
    public static final int STATE_END = 3;

    protected final JsonWriteContext parent;
    protected int state;

    public JsonWriteContext(JsonWriteContext parent, int state) {
        this.parent = parent;
        this.state = state;
    }

    public JsonWriteContext createObjectContext() throws JsonException {
        newValue();

        return new ObjectWriteContext(this);
    }

    public JsonWriteContext createArrayContext() throws JsonException {
        newValue();
        
        return new ArrayWriteContext(this);
    }

    public abstract void newKey() throws JsonException;

    public abstract void newValue() throws JsonException;

    public abstract void newField() throws JsonException;

    public abstract JsonWriteContext close() throws JsonException;

    public boolean isRoot() {
        return false;
    }

    public boolean isObject() {
        return false;
    }

    public boolean isArray() {
        return false;
    }

    public boolean needComma() {
        return state == STATE_AFTER_VALUE;
    }
}