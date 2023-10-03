package org.example.java.lambdas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateInterface {
    static List<String> strings = new ArrayList<>();
    static {
        strings.add("Test");
        strings.add("Word");
        strings.add("New");
        strings.add("Abstract");
        strings.add("Anonimous");
    }

    public static void main(String[] args) {
        Predicate<String> p1 = s -> s.length() > 3;     // Prediate<T> принимает объект и возвр. boolean
        Predicate<String> p2 = s -> s.startsWith("A");
        List<String> result = strings.stream().filter(p1.and(p2)).collect(Collectors.toList()); // объединение двух условий p1 и p2
        System.out.println(result);

        strings.removeIf(e -> e.length() > 7);  // removeIf приинимает Prediate<T>, который принимает объект и возвр. boolean.
        System.out.println(strings);
    }
}
