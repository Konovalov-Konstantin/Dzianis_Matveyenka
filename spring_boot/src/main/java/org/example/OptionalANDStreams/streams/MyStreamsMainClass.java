package org.example.OptionalANDStreams.streams;

import org.example.OptionalANDStreams.optional.Student;

import java.util.List;
import java.util.stream.Collectors;

public class MyStreamsMainClass {

    public static void main(String[] args) {

        List<String> strings = List.of("11", "44", "33", "22", "55", "88", "66", "12");

        List<Integer> collect = strings.stream()
                .map(s -> s + s)    // map преобразовывает входной элементв другой
                .map(ss -> Integer.valueOf(ss))
                .filter(ssValue -> ssValue % 2 == 0)
                .sorted()     // сортировка
//                .distinct()   // убирает дубликаты
//                .skip(1)    // пропустит один первый элемент результата
//                .limit(2)   // ограничит кол-во эл-тов на выходе
                .peek(System.out::println)  // метод 'peek' похож на 'forEach', но он не терминальный, т.е. после него можно продолжать цепочку вызовов
//                .forEach(System.out::println);
                .collect(Collectors.toList());


        List.of(
                        new Student(25, "Petr1"),
                        new Student(35, "Petr2"),
                        new Student(45, "Petr3"),
                        new Student(26, "Petr4"),
                        new Student(36, "Petr5"),
                        new Student(46, "Petr6"),
                        new Student(30, "Petr7"),
                        new Student(15, "Petr8"),
                        new Student(18, "Petr9"),
                        new Student(19, "Petr10")
                )
                .stream()
//                .parallelStream()                                     // можно выполнить вычисления/ поиск паралельно
//                .parallel()                                           // можно выполнить вычисления/ поиск паралельно
                .map(student -> student.getAge())
//                .reduce((age1, age2) -> Math.max(age1, age2))         // с reduce можно найти максимум
                .reduce((age1, age2) -> Integer.sum(age1, age2))        // с reduce можно найти сумму, количество
                .ifPresent(System.out::println);




    }
}
