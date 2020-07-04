package fr.poulpogaz.json;

/**
 * Signals that a problem has occurred while reading
 * or writing json.
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonException extends Exception {

    /**
     * Constructs an {@code JsonException} with {@code null}
     * as its error detail message.
     */
    public JsonException() {
        super();
    }

    /**
     * Constructs an {@code JsonException} with the specified detail message.
     *
     * @param message The detail message
     */
    public JsonException(String message) {
        super(message);
    }
}