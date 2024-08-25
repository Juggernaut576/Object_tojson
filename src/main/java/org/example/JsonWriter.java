package org.example;
import java.lang.reflect.Field;
import java.util.*;

public class JsonWriter {

    // Constraints : Input object contains members of the following type only:
    // - String
    // - Integer
    // - Double
    // - List
    public static String toJson(Object o) {
        //StringBuilder json = new StringBuilder();
        Class<?> k = o.getClass();

        String json = "{\n";

        Field fields[] = k.getDeclaredFields();
        for(int i=0;i<fields.length;i++)
        {
            Field field = fields[i];
            field.setAccessible(true);

            try {
                Object value = field.get(o);
                json += " \"" + field.getName() + "\": ";
                if (value instanceof String) {
                    json += "\"" + value + "\"";
                } else if (value instanceof Integer || value instanceof Double) {
                    json += value;
                } else if (value instanceof List) {
                    json += listToJson((List<?>) value);
                }

                if (i < fields.length - 1) {
                    json += ",";
                }
                json += "\n";
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        json += "}";
        return json;
    }

    private static String listToJson(List<?> list)
    {
        String json = "[";
        for(int i=0;i< list.size();i++)
        {
            Object item = list.get(i);
            if(item instanceof String)
            {
                json += "\"" + item + "\"";
            }
            else if (item instanceof Integer || item instanceof Double)
            {
              json += item;
            }
            else if(item instanceof List)
            {
                json += listToJson((List<?>) item);
            }

            if(i<list.size()-1)
            {
                json += ", ";
            }
        }
        json += "]";
        return json;
    }
}
