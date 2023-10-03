package org.example.java.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Вудуеу {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("1", "8", "6", "4", "2");
        List<Integer> collect = strings.stream()
                .filter(x -> Integer.valueOf(x) > 4)
                .map(x -> Integer.valueOf(x) * 10)
                .sorted((x, y) -> y - x)
                .collect(Collectors.toList());


        System.out.println(strings);
        System.out.println(collect);
    }


}
