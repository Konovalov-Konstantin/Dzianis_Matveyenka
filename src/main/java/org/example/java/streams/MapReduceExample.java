package org.example.java.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MapReduceExample {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>(1000000);
        for (int i = 0; i < 1000000; i++) {
            students.add(new Student((int) (Math.random() * 1000000), "A"));
        }

        long start = System.currentTimeMillis();
        Student student = students.stream().max((o1, o2) -> o1.age - o2.getAge()).get();
        long end = System.currentTimeMillis();
        System.out.println("Стрим: " + (end - start) + ", max = " + student.age);

        long start1 = System.currentTimeMillis();
        Optional<Integer> reduce = students.stream()
                .parallel()// разделение данных на несколько параллельных потоков
                .map(s -> s.getAge())
                .reduce((age1, age2) -> Math.max(age1, age2));// сравнение двух чисел и выбор максимального

//                .ifPresent(System.out::println);
        long end1 = System.currentTimeMillis();
        System.out.println("Map/Reduce: " + (end1 - start1) + ", max = " + (reduce));
    }


    static class Student {
        int age;
        String name;

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
}
