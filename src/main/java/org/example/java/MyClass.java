package org.example.java;


import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyClass {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("c", "3");
        map.put("d", "1");
        Collection<String> values = map.values();
        Set<String> uniqValues = new HashSet<>(values);
        System.out.println(values.size() == uniqValues.size());


        // BinaryOperator
        BinaryOperator<Integer> bo = (x, y) -> x * y;
        System.out.println(bo.apply(10, 20));
        System.out.println(Stream.of("Строка-1 ", "Строка-2", "Строка-3", "Строка-4").reduce((x, y) -> x.concat(y)).get());
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7).map(x -> x.toString()).reduce((x, y) -> x + " " + y));

        // BinaryOperator - сумирование
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7).reduce(0, (x, y) -> x + y));

        // UnaryOperator
        UnaryOperator<String> uo = s -> s.concat(" что-то");
        System.out.println(uo.apply("Это"));

    }
}



