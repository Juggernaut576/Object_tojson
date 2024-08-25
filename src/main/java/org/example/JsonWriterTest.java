package org.example;
import java.util.*;

public class JsonWriterTest {

    public static void main(String[] args) {

        testToJsonString();
        testToJsonInt();
        testToJsonList();

    }

    static class TestClass1 {
        public String name;
    }

    static class TestClass2 {
        public Integer age;
    }

    static class TestClass3 {
        public List<String> items;
    }

    public static void testToJsonString() {

        TestClass1 obj = new TestClass1();
        obj.name="test1";

        String json = JsonWriter.toJson(obj);

        // Strategy 3
        // {"name"      :     "test1"}
        String[] splits = json.split(":");
        if (splits.length != 2) {
            throw new RuntimeException("Incorrect json format:"+json);
        }

        // '{"name"     '
        // '"name"     '
        // '"name"'
        String part1 = splits[0].replace("{", "").strip();
        if (!part1.equals("\"name\"")) {
            throw new RuntimeException("Incorrect key in JSON " + part1);
        }

        String part2 = splits[1].replace("}","").strip();
        if (!part2.equals("\"test1\""))
        {
            throw new RuntimeException("Incorrect value in JSON " + part2);
        }
        System.out.println("testToJsonString passed.");
    }

    public static void testToJsonInt() {
         TestClass2 obj = new TestClass2();
         obj.age = 25;

         String json = JsonWriter.toJson(obj);
         String[] splits = json.split(":");
         if(splits.length!=2)
         {
             throw new RuntimeException("Incorrect JSON format:" + json);
         }

         String part1 = splits[0].replace("{","").strip();
         if(!part1.equals("\"age\""))
         {
             throw new RuntimeException("Incorrect key in JSON: " + part1);
         }

         String part2 = splits[1].replace("}","").strip().replace("\n","");
         if(!part2.matches("\\d+"))
         {
             throw new RuntimeException("The value is not an integer: " + part2);
         }
        System.out.println("testToJsonInt Passed.");
    }

    public static void testToJsonList() {
      TestClass3 obj = new TestClass3();
      obj.items = Arrays.asList("item1","item2","item3","item4");
      String json = JsonWriter.toJson(obj);

      String[] splits = json.split(":");
      if(splits.length!=2)
      {
          throw new RuntimeException("Incorrect JSON format: " + json);
      }

      String part1 = splits[0].replace("{","").strip();
      if(!part1.equals("\"items\""))
        {
            throw new RuntimeException("Incorrect key in JSON " + part1);
        }

      String part2 = splits[1].replace("}","").strip().replace("\n","");

      if(!part2.startsWith("[")||!part2.endsWith("]"))
      {
          throw new RuntimeException("Incorrect list format in JSON " + part2);
      }

      String listContent = part2.substring(1,part2.length()-1).strip();

      String[] jsonItems = listContent.split(",\\s*");
      if (jsonItems.length != obj.items.size())
      {
          throw new RuntimeException("Mismatch in list size. Expected " + obj.items.size() + " elements, found " + jsonItems.length);
      }

      for(int i=0;i<obj.items.size();i++)
      {
          String expectedItem = "\"" + obj.items.get(i) + "\"";
          if(!jsonItems[i].equals(expectedItem))
          {
              throw new RuntimeException("Mismatch in list item at index " + i + ". Expected: " + expectedItem + ", Found: " + jsonItems[i]);
          }
      }
        System.out.println("testToJsonList passed.");
    }
}
