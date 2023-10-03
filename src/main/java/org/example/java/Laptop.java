package org.example.java;

public class Laptop extends Computer {
    @Override
    public void load() {
        System.out.println("DEF");
    }

    public void printsmth(){
        System.out.println("Что-то из ЛЭПТОПа");
    }
}
