package fr.poulpogaz.json.scope;

import fr.poulpogaz.json.JsonException;

/**
 * A base class for checking syntax while
 * writing
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public abstract class JsonWriteScope {

    public static final int STATE_EMPTY = 0;
    public static final int STATE_EXPECT_VALUE = 1;
    public static final int STATE_AFTER_VALUE = 2;
    public static final int STATE_END = 3;

    protected final JsonWriteScope parent;
    protected int state;

    public JsonWriteScope(JsonWriteScope parent, int state) {
        this.parent = parent;
        this.state = state;
    }

    public JsonWriteScope createObjectScope() throws JsonException {
        newValue();

        return new ObjectWriteScope(this);
    }

    public JsonWriteScope createArrayScope() throws JsonException {
        newValue();
        
        return new ArrayWriteScope(this);
    }

    public abstract void newKey() throws JsonException;

    public abstract void newValue() throws JsonException;

    public abstract void newField() throws JsonException;

    public abstract JsonWriteScope close() throws JsonException;

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