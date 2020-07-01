package fr.poulpogaz.json;

import com.sun.jdi.InternalException;
import fr.poulpogaz.json.number.FirstDigitContext;
import fr.poulpogaz.json.number.JsonNumberContext;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonReader extends AbstractJsonReader {

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

        JsonNumberContext context = new FirstDigitContext();

        while (context != null) {
            char next = peekNextChar();

            switch (next) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> context.newDigit(next);
                case '-' -> context.newHyphen();
                case '+' -> context.newPlus();
                case 'e', 'E' -> context = context.newExponent();
                case '.' -> context = context.newPoint();
                default -> {
                    context.close();
                    context = null;
                }
            }

            if (context != null) {
                nextChar(); // consume next char
                builder.append(next);
            }
        }

        return new BigDecimal(builder.toString());
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
            return JsonToken.BIG_DECIMAL_TOKEN;
        } else if (number instanceof BigInteger) {
            return JsonToken.BIG_INTEGER_TOKEN;
        } else if (number instanceof Long) {
            return JsonToken.LONG_TOKEN;
        } else if (number instanceof Float) {
            return JsonToken.FLOAT_TOKEN;
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