package fr.poulpogaz.json.context;

import fr.poulpogaz.json.JsonException;

public abstract class JsonReadContext {

    public static JsonReadContext createRootContext() {
        return new RootReadContext();
    }

    public static final int STATE_EMPTY = 0;
    public static final int STATE_EXPECT_KEY = 1;
    public static final int STATE_EXPECT_COLON = 2;
    public static final int STATE_EXPECT_VALUE = 3;
    public static final int STATE_AFTER_VALUE = 4;
    public static final int STATE_END = 5;

    protected final JsonReadContext parent;
    protected int state;

    public JsonReadContext(JsonReadContext parent, int state) {
        this.parent = parent;
        this.state = state;
    }

    public JsonReadContext createObjectContext() throws JsonException {
        newValue();

        return new ObjectReadContext(this);
    }

    public JsonReadContext createArrayContext() throws JsonException {
        newValue();

        return new ArrayReadContext(this);
    }

    public abstract JsonReadContext close() throws JsonException;

    public abstract void newKey() throws JsonException;

    public abstract boolean mayNeedKey();

    public abstract void newValue() throws JsonException;

    public abstract void newComma() throws JsonException;

    public abstract void newColon() throws JsonException;
}