package org.example;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        // Example usage
        List<String> subjectIds1 = List.of("Math", "Physics", "Chemistry");
        List<String> subjectIds2 = List.of("Math", "Physics", "Chemistry");

        // Creating the Student object
        Student student = new Student("John", 20, 85.5, subjectIds1);
        Student student1 = new Student("Arjun", 20, 90, subjectIds2);

        // Convert the Student object to JSON and print it
        String jsonString1 = student.toJson();
        String jsonString2 = student1.toJson();

        System.out.println(jsonString1);
        System.out.println(jsonString2);
    }
}