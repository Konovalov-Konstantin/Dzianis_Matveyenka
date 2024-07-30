package org.example.OptionalANDStreams.optional;

import java.util.Optional;
import java.util.stream.Stream;

public class MyOptionalMainClasss {
    public static void main(String[] args) {

        Optional<Student> result = Stream.of(
                new Student(25, "Anna"),
                new Student(30, "Ivan"),
                new Student(18, "Petr"),
                new Student(35, "John")
        )
//                .flatMap(student -> student.marks.stream())    // со стримами flatmap должен вернуть другой stream
                .reduce((student1, student2) -> (student1.getAge() > student2.getAge() ? student1 : student2));

        result.ifPresent(System.out::println);

        result.filter(x -> x.age > 100)
                .ifPresentOrElse(System.out::println, () -> System.out.println("NO NO NO"));

        result.map(x -> x.age)
                .filter(age -> age > 30)
                .flatMap(age -> Optional.of(age * 2))     // c Optional flatmap в отличие от map должен возвращать Optional. Flatmap разворачивает Optional, который возвращает вызываемый метод
                .ifPresent(System.out::println);

        // OptionalOfNullable оборачивает значение, которое может быть null
        // OptionalOf оборачивает значение, которое гарантированно НЕ может быть null, иначе будет NPE
    }
}
