package org.example.java;

public class Computer implements MyInterface {
    public void load() {
        System.out.println("ABC");
    }

    @Override
    public void doSmth() {
        System.out.println("From MyInterface");
    }
}
