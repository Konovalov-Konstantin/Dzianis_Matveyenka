package org.example.java;

import java.util.Random;

public interface MyInterface {
    public final Random RANDOM = new Random();

    void doSmth();

    default void printDefault() {
        System.out.println(getrandom());
        System.out.println("Default from interface");
    }

    int getrandom(){
        return RANDOM.nextInt();
    }

}
