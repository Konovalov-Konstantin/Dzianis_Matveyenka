package org.example.java.generics;

public class TestGenerics<T> {
    private T[] massive;
    private int size;

    public TestGenerics(int capacity) {
        this.massive = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (size < massive.length) {
            massive[size++] = element;
        } else {
            throw new RuntimeException("Нет Мест");
        }
    }

    public T get(int index) {
        return massive[index];
    }

    public static void main(String[] args) {
        // указываем конкретный тип
        TestGenerics<String> testString = new TestGenerics<>(10);
        for (int i = 0; i < 11; i++) {
            testString.add("test");
        }

        // указываем конкретный тип
        TestGenerics<Integer> testInt = new TestGenerics<>(5);
        testInt.add(15);
    }

}
