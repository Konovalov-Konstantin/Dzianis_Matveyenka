package org.example.java.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class optionalClass {

    public static final int SIZE = 100;

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            students.add(new Student((int) (Math.random() * SIZE), "A"));
        }

        students.stream()
                .filter(x -> x.getAge() < 20)
                .findFirst()
                .flatMap(x -> Optional.of(x.getAge()))  //flatMap должен возвратить Optional, поэтому применен Optional.of()
                                // Optional.of(...) - гарантированно должен вернуть не null, иначе будет NPE
                                // Optional.ofNullable(...) - вернет объект Optional в т.ч. если вернеся null.
                .ifPresent(System.out::println);
    }

}

class Student {
    private int age;
    private String name;

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
}
