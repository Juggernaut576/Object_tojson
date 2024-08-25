package org.example;

import java.util.List;

class Student {
    private String name;
    private int age;
    private double score;
    private List<String> subjectIds;

    public Student(String name, int age, double score, List<String> subjectIds) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.subjectIds = subjectIds;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getScore() {
        return score;
    }

    public List<String> getSubjectIds() {
        return subjectIds;
    }

    // Convert object to JSON string with formatting
    public String toJson() {
        String json = "{\n";

        // Handle 'name' field (String)
        json += "\t\"name\": ";
        if (getName() != null) {
            json += "\"" + getName() + "\"";
        } else {
            json += "null";
        }
        json += ",\n";

        // Handle 'age' field (int)
        json += "\t\"age\": ";
        if (getAge() != 0) {
            json += getAge();
        } else {
            json += "0";
        }
        json += ",\n";

        // Handle 'score' field (double)
        json += "\t\"score\": ";
        if (getScore() != 0.0) {
            json += getScore();
        } else {
            json += "0.0";
        }
        json += ",\n";

        // Handle 'subjectIds' field (List<String>)
        json += "\t\"subjectIds\": ";
        if (getSubjectIds() != null && !getSubjectIds().isEmpty()) {
            json += "[";
            for (int i = 0; i < getSubjectIds().size(); i++) {
                json += "\"" + getSubjectIds().get(i) + "\"";
                if (i < getSubjectIds().size() - 1) {
                    json += ", ";
                }
            }
            json += "]";
        } else {
            json += "[]";
        }

        json += "\n}";
        return json;
    }
}
