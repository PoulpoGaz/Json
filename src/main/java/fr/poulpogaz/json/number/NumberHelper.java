package fr.poulpogaz.json.number;

import fr.poulpogaz.json.JsonException;

public class NumberHelper extends JsonNumberContext{

    private JsonNumberContext context;

    private final StringBuilder digitPart = new StringBuilder();
    private final StringBuilder fractionalPart = new StringBuilder();
    private int exponent;
    private int trailingZeroLength;

    private boolean closed = false;
    private boolean isNegativeExponent = false;
    private boolean isNegative = false;

    public NumberHelper() {
        context = new FirstDigitContext();
    }

    @Override
    public void newDigit(char digit) throws JsonException {
        context.newDigit(digit);

        if (context.isFirstDigitContext()) {
            digitPart.append(digit);

            if (digit == '0') {
                trailingZeroLength++;
            } else {
                trailingZeroLength = 0;
            }

        } else if (context.isFractionContext()) {
            fractionalPart.append(digit);
        } else if (context.isExponentContext()) { // more difficult when there's an exponent
            exponent = exponent * 10 + (digit - '0');
        }
    }

    @Override
    public void newHyphen() throws JsonException {
        context.newHyphen();

        if (context.isFirstDigitContext()) {
            isNegative = true;
        } else if (context.isExponentContext()) {
            isNegativeExponent = true;
        }
    }

    @Override
    public void newPlus() throws JsonException {
        context.newPlus();
    }

    @Override
    public JsonNumberContext newExponent() throws JsonException {
        context = context.newExponent();

        return context;
    }

    @Override
    public JsonNumberContext newPoint() throws JsonException {
        context = context.newPoint();

        return context;
    }

    @Override
    public void close() throws JsonException {
        context.close();

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