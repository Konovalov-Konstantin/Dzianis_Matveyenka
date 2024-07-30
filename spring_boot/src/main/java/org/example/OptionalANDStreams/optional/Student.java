package org.example.OptionalANDStreams.optional;

import java.util.ArrayList;
import java.util.List;

public class Student {
    int age;
    String name;
    List<Integer> marks = new ArrayList<>();

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }



    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
