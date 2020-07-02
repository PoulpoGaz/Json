package fr.poulpogaz.json;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumberTest {

    @Test
    void intTest() throws IOException, JsonException {
        int[] ints =   {50, -50,  50,  -50,     5,     -5,    55,    -55,   550,   -550, Integer.MAX_VALUE, Integer.MIN_VALUE};
        String json = "[50, -50, 5e1, -5e1, 50e-1, -50e-1, 5.5e1, -5.5e1, 5.5e2, -5.5e2,        2147483647,       -2147483648]";

        IJsonReader reader = new JsonReader(new StringReader(json));

        reader.beginArray();

        int i = 0;
        while (!reader.isArrayEnd()) {
            if (reader.hasNextInt()) {
                int next = reader.nextInt();

                assertEquals(ints[i++], next);
            } else {
                throw new IllegalStateException("expected " + ints[i] + " (type int) but was " + reader.next() + " (i=" + i + ")");
            }
        }

        reader.endArray();

        reader.close();
    }

    @Test
    void longTest() throws IOException, JsonException {
        long[] longs =  {     Long.MAX_VALUE,       Long.MIN_VALUE, (long) 1e17, (long) 1.1e17, (long) -1e17, (long) -1.1e17,           (long) 1e17,           (long) -1e17};
        String json = "[9223372036854775807, -9223372036854775808,        1e17,        1.1e17,        -1e17,        -1.1e17, 1000000000000000000e-1, -1000000000000000000e-1]";

        IJsonReader reader = new JsonReader(new StringReader(json));

        reader.beginArray();

        int i = 0;
        while (!reader.isArrayEnd()) {
            if (reader.hasNextLong()) {
                long next = reader.nextLong();

                assertEquals(longs[i++], next);
            } else {
                throw new IllegalStateException("expected " + longs[i] + " (type long) but was " + reader.next() + " (i=" + i + ")");
            }
        }

        reader.endArray();

        reader.close();
    }

    private BigInteger bigInteger(String val) {
        return new BigDecimal(val).toBigIntegerExact();
    }

    @Test
    void bigIntegerTest() throws IOException, JsonException {
        BigInteger[] bigIntegers = {bigInteger("1e100"), bigInteger("-1e100"), bigInteger("1.5e100"), bigInteger("-1.5e100"),
                bigInteger("1000000000000000000000000000000e-1"), bigInteger("-1000000000000000000000000000000e-1"),
                bigInteger("1000000000000000000000000000000.1234e5"), bigInteger("1000000000000000000000000000000.1234e5")};
        String json = "[1e100, -1e100, 1.5e100, -1.5e100, 1000000000000000000000000000000e-1, -1000000000000000000000000000000e-1]," +
                "1000000000000000000000000000000.1234e5, 1000000000000000000000000000000.1234e5";

        IJsonReader reader = new JsonReader(new StringReader(json));

        reader.beginArray();

        int i = 0;
        while (!reader.isArrayEnd()) {
            if (reader.hasNextBigInteger()) {
                BigInteger next = reader.nextBigInteger();

                assertEquals(bigIntegers[i++], next);
            } else {
                throw new IllegalStateException("expected " + bigIntegers[i] + " (type big integer) but was " + reader.next() + " (i=" + i + ")");
            }
        }

        reader.endArray();

        reader.close();
    }

    @Test
    void floatTest() throws IOException, JsonException {
        float[] floats =  {5.3f, -5.3f, 5.1e-1f, -5.1e-1f};
        String json = "[5.3, -5.3, 5.1e-1, -5.1e-1]";

        IJsonReader reader = new JsonReader(new StringReader(json));

        reader.beginArray();

        int i = 0;
        while (!reader.isArrayEnd()) {
            if (reader.hasNextDecimalNumber()) {
                float next = reader.nextFloat();

                assertEquals(floats[i++], next);
            } else {
                throw new IllegalStateException("expected " + floats[i] + " (decimal) but was " + reader.next() + " (i=" + i + ")");
            }
        }

        reader.endArray();

        reader.close();
    }
}