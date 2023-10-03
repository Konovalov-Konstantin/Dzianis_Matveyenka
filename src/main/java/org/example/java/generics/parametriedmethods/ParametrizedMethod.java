package org.example.java.generics.parametriedmethods;

import java.util.ArrayList;

public class ParametrizedMethod {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Integer element = MyClass.getElement(list);
        System.out.println(element);
    }
}

class MyClass {
    public static <T> T getElement (ArrayList<T> list) {
        return list.get(0);
    }
}
