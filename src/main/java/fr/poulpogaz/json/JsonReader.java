package fr.poulpogaz.json;

import com.sun.jdi.InternalException;
import fr.poulpogaz.json.number.FirstDigitContext;
import fr.poulpogaz.json.number.JsonNumberContext;
import fr.poulpogaz.json.number.NumberHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;

public class JsonReader extends AbstractJsonReader {

    private static final char[] LONG = {'9', '2','2','3', '3','7','2', '0','3','6', '8','5','4', '7','7','5', '8','0','8'};;

    private static final int START = -2;
    private static final int EOF = -1;

    private char[] buffer = new char[4096];
    private int pos = START;

    public JsonReader(InputStream in) {
        super(in);
    }

    public JsonReader(Reader in) {
        super(in);
    }

    public void nextToken() throws IOException, JsonException {
        if (currentToken != null || isClosed()) {
            return;
        }

        while (true) {
            skipWhiteSpaces();

            if (pos < 0) { // EOF
                context.close();
                currentToken = JsonToken.END_TOKEN;
                return;
            }

            char c = peekNextChar();

            switch (c) {
                case '{' -> {
                    context = context.createObjectContext();
                    setCurrentToken(JsonToken.BEGIN_OBJECT_TOKEN);
                    pos++;
                    return;
                }
                case '}' -> {
                    context = context.close();
                    setCurrentToken(JsonToken.END_OBJECT_TOKEN);
                    pos++;
                    return;
                }
                case '[' -> {
                    context = context.createArrayContext();
                    setCurrentToken(JsonToken.BEGIN_ARRAY_TOKEN);
                    pos++;
                    return;
                }
                case ']' -> {
                    context = context.close();
                    setCurrentToken(JsonToken.END_ARRAY_TOKEN);
                    pos++;
                    return;
                }
                case ',' -> {
                    context.newComma();
                    pos++;
                }
                case ':' -> {
                    context.newColon();
                    pos++;
                }
                case '"' -> {
                    stringToken = parseString();

                    if (context.mayNeedKey()) {
                        context.newKey();

                        setCurrentToken(JsonToken.KEY_TOKEN);
                    } else {
                        context.newValue();
                        setCurrentToken(JsonToken.STRING_TOKEN);
                    }
                    return;
                }
                case 'n' -> {
                    context.newValue();
                    if (match("null")) { // null
                        setCurrentToken(JsonToken.NULL_TOKEN);
                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case 't' -> {
                    context.newValue();
                    if (match("true")) { // true
                        booleanToken = true;
                        setCurrentToken(JsonToken.BOOLEAN_TOKEN);

                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case 'f' -> {
                    context.newValue();
                    if (match("false")) { // false
                        booleanToken = false;
                        setCurrentToken(JsonToken.BOOLEAN_TOKEN);

                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-' -> {
                    context.newValue();
                    numberToken = parseNumber();
                    setCurrentToken(toToken(numberToken));
                    return;
                }
                default -> throw new IOException("Illegal character: " + c + " (bytes: " + (byte) c + ") at " + pos);
            }
        }
    }

    private boolean match(String str) throws IOException, JsonException {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c != nextChar()) {
                return false;
            }
        }

        return true;
    }

    private Number parseNumber() throws JsonException, IOException {
        StringBuilder builder = new StringBuilder();

        NumberHelper helper = new NumberHelper();

        while (!helper.isClosed()) {
            char next = peekNextChar();

            switch (next) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> helper.newDigit(next);
                case '-' -> helper.newHyphen();
                case '+' -> helper.newPlus();
                case 'e', 'E' -> helper.newExponent();
                case '.' -> helper.newPoint();
                default -> helper.close();
            }

            if (!helper.isClosed()) {
                nextChar(); // consume next char
                builder.append(next);
            }
        }


        if (isInteger(helper)) { // integer number
            int digit = helper.getDigitPart().length();
            int fraction = helper.getFractionalPart().length();

            int length = digit + (helper.isNegativeExponent() ? -helper.getExponent() : helper.getExponent());

            if (length <= 19) {

                int exponent = helper.getExponent();

                if (fraction != 0) {
                    exponent -= fraction;

                    removeFraction(builder);
                }

                if (exponent != 0) {
                    removeExponent(builder, helper.isNegativeExponent(), exponent);
                }

                if (length < 10) { // int
                    return Integer.parseInt(builder.toString());

                } else if (length == 10) { // int or long     2 147 483 647 = max int value
                    long l = Long.parseLong(builder.toString());

                    if ((int) l == l) {
                        return (int) l; // important casting, used for setting set correct token
                    } else {
                        return l;
                    }

                } else if (length < 19) { // long
                    return Long.parseLong(builder.toString());

                } else  { // long or big integer      9 223 372 036 854 775 808 = max long value
                    String number = builder.toString();

                    char[] nArray = number.toCharArray();

                    int j = helper.isNegative() ? 1 : 0;

                    for (int i = 0; i < length; i++) {
                        if (nArray[j + i] > LONG[i]) {
                            return new BigDecimal(number).toBigIntegerExact();
                        }
                    }

                    return Long.parseLong(number);
                }
            } else { // big integer

                // making at first a big decimal
                // because big integer constructor
                // cannot handle non java int number
                return new BigDecimal(builder.toString()).toBigIntegerExact();
            }

        } else { // decimal number

            return new BigDecimal(builder.toString()); // no float or double parsing
        }
    }

    private boolean isInteger(NumberHelper helper) {
        String fraction = helper.getFractionalPart();

        if (fraction.length() > 0) { // has a fractional part, may be removed

            if (helper.getExponent() == 0) { // has a fractional part but no exponent
                return false;
            } else if (helper.isNegativeExponent()) { // has a fractional part and a negative exponent
                return false;
            } else { // has a fractional part and a positive exponent
                return fraction.length() < helper.getExponent(); // check if exponent can remove the fractional part
            }

        } else { // no fractional part
            if (helper.isNegativeExponent() && helper.getExponent() > 0) { // negative exponent, need to check trailing zeros
                int nZero = helper.getTrailingZeroLength();

                return nZero >= helper.getExponent();
            } else { // may have an exponent, but we don't care
                return true;
            }
        }
    }

    // Utility method for integer
    private void removeFraction(StringBuilder number) {
        int pointIndex = number.indexOf(".");

        number.replace(pointIndex, pointIndex + 1, "");
    }

    // Utility method for integer
    private void removeExponent(StringBuilder builder, boolean negativeExponent, int exponent) {
        int exponentIndex = builder.indexOf("e");

        if (negativeExponent) { // negative
            builder.replace(exponentIndex - exponent, builder.length(), ""); // Works only if the string is an integer
        } else { // positive
            builder.replace(exponentIndex, builder.length(), "0".repeat(exponent));
        }
    }

    private String parseString() throws JsonException, IOException {
        StringBuilder builder = new StringBuilder();

        nextChar(); // Consume the peeked char, which is a double quote

        while (true) {
            refillBuffer();

            char c = nextChar();

            if (c == '\\') {
                refillBuffer();
                char next = nextChar();

                switch (next) {
                    case '\\' -> builder.append('\\');
                    case '"' -> builder.append('"');
                    case '/' -> builder.append('/');
                    case 'b' -> builder.append('\b');
                    case 'f' -> builder.append('\f');
                    case 'n' -> builder.append('\n');
                    case 'r' -> builder.append('\r');
                    case 't' -> builder.append('\t');
                    case 'u' -> builder.append(parseHex());
                    default -> throw new JsonException("Unknown escape character: " + next + " (bytes: " + (byte) +next + ")");
                }
            } else if (c == '"') {
                break;
            } else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    private char parseHex() throws IOException, JsonException {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            builder.append(nextChar());
        }

        return (char) Integer.parseInt(builder.toString(), 16);
    }

    /**
     * Refill is needed the buffer
     * @throws IOException If an I/O error occurs
     */
    private void refillBuffer() throws IOException {
        if (pos >= buffer.length || pos == START) {
            int read = in.read(buffer);

            if (read >= 0) {
                char[] newBuffer = new char[read];
                System.arraycopy(buffer, 0, newBuffer, 0, newBuffer.length);
                buffer = newBuffer;

                pos = 0;
            } else {
                pos = EOF;
            }
        }
    }

    /**
     * @return the next char and consume it
     * @throws JsonException If end of file is reached
     * @throws IOException If an I/O error occurs
     */
    private char nextChar() throws JsonException, IOException {
        refillBuffer();

        if (pos == EOF) {
            throw new JsonException("EOF");
        } else {
            return buffer[pos++];
        }
    }

    private char peekNextChar() throws JsonException, IOException {
        if (pos >= buffer.length) { // need to increase the buffer
            int read = in.read();

            if (read == -1) {
                throw new JsonException("EOF");
            }

            char[] newBuffer = new char[buffer.length + 1];
            System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
            newBuffer[buffer.length] = (char) read;

            buffer = newBuffer;
        }

        return buffer[pos];
    }

    private void skipWhiteSpaces() throws IOException{
        while (true) {
            refillBuffer();

            if (pos < 0) {
                return;
            }

            char c = buffer[pos];

            switch (c) {
                case ' ':
                case '\n':
                case '\f':
                case '\r':
                case '\t':
                    pos++;
                    continue;
                default:
                    return;
            }
        }
    }

    private JsonToken toToken(Number number) {
        if (number instanceof BigDecimal) {
            return JsonToken.DECIMAL_TOKEN;
        } else if (number instanceof BigInteger) {
            return JsonToken.BIG_INTEGER_TOKEN;
        } else if (number instanceof Long) {
            return JsonToken.LONG_TOKEN;
        } else if (number instanceof Integer) {
            return JsonToken.INT_TOKEN;
        }  else {
            throw new InternalException("Unknown number: " + number + " (" + number.getClass() + ")");
        }
    }

    private void setCurrentToken(JsonToken token) {
        if (currentToken == null) {
            currentToken = token;
        }
    }
}