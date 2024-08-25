package org.example;


public class JsonWriterTest {

    public static void main(String[] args) {

        testToJsonString();
        testToJsonInt();
        testToJsonList();

    }

    static class TestClass1 {
        public String name;
    }

    public static void testToJsonString() {

        TestClass1 obj = new TestClass1();
        obj.name="test1";

        String json = JsonWriter.toJson(obj);

        // Strategy 3
        // {"name"      :     "test1"}
        String[] splits = json.split(":");
        if (splits.length != 2) {
            throw new RuntimeException();
        }

        // '{"name"     '
        // '"name"     '
        // '"name"'
        String part1 = splits[0].replace("{", "");
        part1 = part1.strip();
        if (!part1.equals("\"name\"")) {
            throw new RuntimeException();
        }

        // TODO: Check part 2

    }

    public static void testToJsonInt() {

    }

    public static void testToJsonList() {
    }
}
