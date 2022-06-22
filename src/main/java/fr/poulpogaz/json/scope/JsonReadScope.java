package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A base class for checking syntax while
 * reading a json stream
 *
 * @author PoulpoGaz
 * @version 1.2.1
 */
public abstract class JsonReadScope {

    public static final int STATE_EMPTY = 0;
    public static final int STATE_EXPECT_KEY = 1;
    public static final int STATE_EXPECT_COLON = 2;
    public static final int STATE_EXPECT_VALUE = 3;
    public static final int STATE_AFTER_VALUE = 4;
    public static final int STATE_END = 5;

    protected final JsonReadScope parent;

    protected int state;

    public JsonReadScope(JsonReadScope parent) {
        this.parent = parent;
        this.state = STATE_EMPTY;
    }

    public JsonReadScope createObjectScope() throws JsonException {
        newValue();

        return new ObjectReadScope(this);
    }

    public JsonReadScope createArrayScope() throws JsonException {
        newValue();

        return new ArrayReadScope(this);
    }

    public abstract JsonReadScope close() throws JsonException;

    public abstract void newKey() throws JsonException;

    public abstract boolean mayNeedKey();

    public abstract void newValue() throws JsonException;

    public abstract void newComma() throws JsonException;

    public abstract void newColon() throws JsonException;

    public JsonReadScope getParent() {
        return parent;
    }
}