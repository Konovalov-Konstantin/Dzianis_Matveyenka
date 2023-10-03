package org.example.java.generics.parametrizedclasses;

public class ParametrizedClass {
    public static void main(String[] args) {

        Info<String> stringInfo = new Info<>("Test");       // String
        String stringvalue = stringInfo.getValue();
        System.out.println(stringvalue);

        Info<Integer> intInfo = new Info<>(25);       // int
        Integer intvalue = intInfo.getValue();
        System.out.println(intvalue);
    }
}

class Info<T> {
    private T value;

    public Info(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}