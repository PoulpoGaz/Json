package fr.poulpogaz.json;

import fr.poulpogaz.json.number.NumberHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A json file reader. It reads json file as a
 * stream of tokens defined in the enumeration
 * {@link JsonToken}
 *
 * @author PoulpoGaz
 * @version 1.0
 */
public class JsonReader extends AbstractJsonReader {

    /** Contains all digit of the maximum value of {@code long} **/
    private static final char[] LONG_MAXIMUM = {'9', '2','2','3', '3','7','2', '0','3','6', '8','5','4', '7','7','5', '8','0','7'};

    /** Contains all digit of the minimum value of {@code long} **/
    private static final char[] LONG_MINIMUM = {'9', '2','2','3', '3','7','2', '0','3','6', '8','5','4', '7','7','5', '8','0','8'};

    /** Specials position value **/
    private static final int START = -2;
    private static final int EOF = -1;

    private char[] buffer = new char[4096];
    private int pos = START;

    /**
     * Creates a new instance that reads a json stream from an
     * {@link InputStream}
     *
     * @param in a json encoded input stream
     */
    public JsonReader(InputStream in) {
        super(in);
    }

    /**
     * Creates a new instance that reads a json stream from an
     * {@link Reader}
     *
     * @param in a json encoded reader
     */
    public JsonReader(Reader in) {
        super(in);
    }

    /**
     * Peek the next token. The token value is stored in
     * {@link #currentToken}. His value can be stored
     * in {@link #numberToken}, {@link #stringToken} or
     * {@link #booleanToken}
     *
     * @throws IOException If an I/O errors occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    public void peek() throws IOException, JsonException {
        if (currentToken != null || isClosed()) {
            return;
        }

        while (true) {
            skipWhiteSpaces();

            if (pos < 0) { // EOF
                scope.close();
                currentToken = JsonToken.END_TOKEN;
                return;
            }

            char c = peekNextChar();

            switch (c) {
                case '{' -> {
                    scope = scope.createObjectScope();
                    setCurrentToken(JsonToken.BEGIN_OBJECT_TOKEN);
                    pos++;
                    return;
                }
                case '}' -> {
                    scope = scope.close();
                    setCurrentToken(JsonToken.END_OBJECT_TOKEN);
                    pos++;
                    return;
                }
                case '[' -> {
                    scope = scope.createArrayScope();
                    setCurrentToken(JsonToken.BEGIN_ARRAY_TOKEN);
                    pos++;
                    return;
                }
                case ']' -> {
                    scope = scope.close();
                    setCurrentToken(JsonToken.END_ARRAY_TOKEN);
                    pos++;
                    return;
                }
                case ',' -> {
                    scope.newComma();
                    pos++;
                }
                case ':' -> {
                    scope.newColon();
                    pos++;
                }
                case '"' -> {
                    stringToken = parseString();

                    if (scope.mayNeedKey()) {
                        scope.newKey();

                        setCurrentToken(JsonToken.KEY_TOKEN);
                    } else {
                        scope.newValue();
                        setCurrentToken(JsonToken.STRING_TOKEN);
                    }
                    return;
                }
                case 'n' -> {
                    scope.newValue();
                    if (match("null")) { // null
                        setCurrentToken(JsonToken.NULL_TOKEN);
                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case 't' -> {
                    scope.newValue();
                    if (match("true")) { // true
                        booleanToken = true;
                        setCurrentToken(JsonToken.BOOLEAN_TOKEN);

                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case 'f' -> {
                    scope.newValue();
                    if (match("false")) { // false
                        booleanToken = false;
                        setCurrentToken(JsonToken.BOOLEAN_TOKEN);

                        return;
                    } else {
                        throw new JsonException("Syntax error");
                    }
                }
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-' -> {
                    scope.newValue();
                    numberToken = parseNumber();
                    setCurrentToken(toToken(numberToken));
                    return;
                }
                default -> throw new IOException("Illegal character: " + c + " (bytes: " + (byte) c + ") at " + pos);
            }
        }
    }

    /**
     * returns true if the next characters in the buffer matches the input {@code String}
     *
     * @param str the string to compare with the buffer
     * @return true if the next characters in the buffer matches the input {@code String}
     * @throws IOException If an I/O error occurs
     * @throws JsonException If end of file is reached
     */
    private boolean match(String str) throws IOException, JsonException {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c != nextChar()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Parse the next number if exists.
     *
     * @implNote
     * First, we construct a {@link StringBuilder} which contains
     * all the digit, and extra information about the number. At the same
     * time, we check the syntax with the {@link NumberHelper} class/
     * Then, if the number is a decimal number, we just return
     * a {@link BigDecimal} object, else we try to parse it to a {@code int}
     * or a {@code long} or a {@link BigInteger} depending on the length of
     * the number
     *
     * @return the parsed number
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     * @see NumberHelper
     */
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

                if (exponent != 0 || exponent != helper.getExponent()) {
                    removeExponent(builder, helper.isNegativeExponent(), exponent);
                }

                if (length < 10) { // int
                    return Integer.parseInt(builder.toString());

                } else if (length == 10) { // int or long     2 147 483 647 = max int value
                    long l = Long.parseLong(builder.toString());

                    if ((int) l == l) {
                        return (int) l; // important casting, used for setting the correct token
                    } else {
                        return l;
                    }

                } else if (length < 19) { // long
                    return Long.parseLong(builder.toString());

                } else  { // long or big integer      9 223 372 036 854 775 808 = max long value
                    String number = builder.toString();

                    char[] nArray = number.toCharArray();
                    char[] long_ = helper.isNegative() ? LONG_MINIMUM : LONG_MAXIMUM;

                    int j = helper.isNegative() ? 1 : 0;

                    for (int i = 0; i < length; i++) {
                        if (nArray[i + j] > long_[i]) {
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

    /**
     * Utility method for {@link #parseNumber()} which determines if the
     * number to parse is an integer or a decimal number
     *
     * @param helper the helper class that contains useful information
     * @return true if the number is an integer
     * @see NumberHelper
     */
    private boolean isInteger(NumberHelper helper) {
        String fraction = helper.getFractionalPart();

        if (fraction.length() > 0) { // has a fractional part, may be removed

            if (helper.getExponent() == 0) { // has a fractional part but no exponent
                return false;
            } else if (helper.isNegativeExponent()) { // has a fractional part and a negative exponent
                return false;
            } else { // has a fractional part and a positive exponent
                return fraction.length() <= helper.getExponent(); // check if exponent can remove the fractional part
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


    /**
     * Utility method for integer which just removes the dot in the
     * {@code builder}
     *
     * @param number the {@link StringBuilder} that contains the number
     */
    private void removeFraction(StringBuilder number) {
        int pointIndex = number.indexOf(".");

        number.replace(pointIndex, pointIndex + 1, "");
    }

    /**
     * Utility method for integer which removes the exponent in the
     * {@code builder}
     *
     * @param builder the {@link StringBuilder} that contains the number
     * @param negativeExponent true if the number has a negative exponent
     * @param exponent the value of the exponent
     */
    private void removeExponent(StringBuilder builder, boolean negativeExponent, int exponent) {
        int exponentIndex = builder.indexOf("e");

        if (negativeExponent) { // negative
            builder.replace(exponentIndex - exponent, builder.length(), ""); // Works only if the string is an integer
        } else { // positive
            builder.replace(exponentIndex, builder.length(), "0".repeat(exponent));
        }
    }

    /**
     * Parses the next string if exists
     *
     * @return the parsed string
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
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
                    default -> throw new JsonException("Unknown escape character: " + next + " (bytes: " + (byte) next + ")");
                }
            } else if (c == '"') {
                break;
            } else {
                builder.append(c);
            }
        }

        return builder.toString();
    }

    /**
     * Parse the next hexadecimal string if exists
     *
     * @return the parsed hexadecimal string
     * @throws IOException If an I/O error occurs
     * @throws JsonException IF there is a syntax problem or if end of file is reached
     */
    private char parseHex() throws IOException, JsonException {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            builder.append(nextChar());
        }

        return (char) Integer.parseInt(builder.toString(), 16);
    }

    /**
     * Refills the buffer if needed
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
     * Returns and consumes the next char
     *
     * @return the next char
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

    /**
     * return the char at the current position
     *
     * @return the peeked char
     * @throws JsonException If end of file is reached
     * @throws IOException If an I/O error occurs
     */
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

    /**
     * Skips whitespaces
     *
     * @throws IOException If an I/O error occurs
     */
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

    /**
     * Convert a number to the appropriate token
     * @param number the number to be converted
     * @return the token
     */
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
            throw new IllegalStateException("Unknown number: " + number + " (" + number.getClass() + ")");
        }
    }

    /**
     * Sets the current token.
     *
     * @param token The new token
     */
    private void setCurrentToken(JsonToken token) {
        if (currentToken == null) {
            currentToken = token;
        }
    }
}