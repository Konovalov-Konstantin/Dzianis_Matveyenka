package org.example.java.lambdas;

import java.util.Comparator;

public class MyClass {
    public static void main(String[] args) {

        MyInterface mi = (a1, a2) -> a1 + a2;
        System.out.println(mi.sum(10, 15));

        Comparator<Integer> ic = (o1, o2) -> o1.compareTo(o2);
        System.out.println(ic.compare(10, 15));
    }
}

