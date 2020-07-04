package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

/**
 * @author PoulpoGaz
 * @version 1.0
 */
public class NumberHelper extends JsonNumberScope {

    private JsonNumberScope scope;

    private final StringBuilder digitPart = new StringBuilder();
    private final StringBuilder fractionalPart = new StringBuilder();
    private int exponent;
    private int trailingZeroLength;

    private boolean closed = false;
    private boolean isNegativeExponent = false;
    private boolean isNegative = false;

    public NumberHelper() {
        scope = new FirstDigitScope();
    }

    @Override
    public void newDigit(char digit) throws JsonException {
        scope.newDigit(digit);

        if (scope.isFirstDigitScope()) {
            digitPart.append(digit);

            if (digit == '0') {
                trailingZeroLength++;
            } else {
                trailingZeroLength = 0;
            }

        } else if (scope.isFractionScope()) {
            fractionalPart.append(digit);
        } else if (scope.isExponentScope()) { // more difficult when there's an exponent
            exponent = exponent * 10 + (digit - '0');
        }
    }

    @Override
    public void newHyphen() throws JsonException {
        scope.newHyphen();

        if (scope.isFirstDigitScope()) {
            isNegative = true;
        } else if (scope.isExponentScope()) {
            isNegativeExponent = true;
        }
    }

    @Override
    public void newPlus() throws JsonException {
        scope.newPlus();
    }

    @Override
    public JsonNumberScope newExponent() throws JsonException {
        scope = scope.newExponent();

        return scope;
    }

    @Override
    public JsonNumberScope newPoint() throws JsonException {
        scope = scope.newPoint();

        return scope;
    }

    @Override
    public void close() throws JsonException {
        scope.close();

        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    public String getDigitPart() {
        return digitPart.toString();
    }

    public String getFractionalPart() {
        return fractionalPart.toString();
    }

    public int getExponent() {
        return exponent;
    }

    public boolean isNegativeExponent() {
        return isNegativeExponent;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public int getTrailingZeroLength() {
        return trailingZeroLength;
    }
}