package fr.poulpogaz.json;

import fr.poulpogaz.json.tree.JsonArray;
import fr.poulpogaz.json.tree.JsonObject;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class TestUtils {

    public static final String JSON = """
            {
                "a": "b",
                "c": "\\u0064",
                "e": 0e50,
                "f": 46.43e-40,
                "g": 50,
                "h": null,
                "i": [
                    [
                        "a",
                        "b",
                        "c"
                    ],
                    [
                        "d",
                        "e",
                        "f"
                    ],
                    {
                        "g": true,
                        "h": false,
                        "i": null,
                        "j": {
                        
                        },
                        "k": [
                        
                        ]
                    }
                ]
            }
            """;



    public static final String FIBO_1000 =
            "43466556346569743917588837500855236222358804893410139879820906920710924291203196338966832627312640434634845291242138802169508131353889095639306046460960325840504142685216489899260193254960875204883719871528960";
    public static final BigInteger BIG_INT = new BigInteger(FIBO_1000);
    public static final BigDecimal BIG_DEC = BigDecimal.valueOf(10).divide(BigDecimal.valueOf(166666666), RoundingMode.HALF_UP);

    public static JsonObject createObject() {
        JsonObject object = new JsonObject();
        object.put("byte", Byte.MAX_VALUE);
        object.put("short", Short.MAX_VALUE);
        object.put("int", Integer.MAX_VALUE);
        object.put("long", Long.MAX_VALUE);
        object.put("float", Float.MAX_VALUE);
        object.put("double", Double.MAX_VALUE);
        object.put("bigint", BIG_INT);
        object.put("bigdec", BIG_DEC);
        object.put("string", "lorem ipsum");
        object.put("boolean", true);
        object.putNull("null");

        return object;
    }

    public static JsonArray createArray() {
        JsonArray array = new JsonArray();
        array.add(Byte.MAX_VALUE);
        array.add(Short.MAX_VALUE);
        array.add(Integer.MAX_VALUE);
        array.add(Long.MAX_VALUE);
        array.add(Float.MAX_VALUE);
        array.add(Double.MAX_VALUE);
        array.add(BIG_INT);
        array.add(BIG_DEC);
        array.add("lorem ipsum");
        array.add(true);
        array.addNull();

        return array;
    }

    public static JsonArray arrayObject() {
        JsonArray array = createArray();
        array.add(createObject());

        return array;
    }

    public static JsonObject objectArray() {
        JsonObject object = createObject();
        object.put("array", createArray());

        return object;
    }
}
