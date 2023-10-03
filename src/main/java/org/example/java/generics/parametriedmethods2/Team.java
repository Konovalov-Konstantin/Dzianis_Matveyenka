package org.example.java.generics.parametriedmethods2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team<T> {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    List<T> team = new ArrayList<>();

    public void add(T participant) {
        team.add(participant);
    }

    public String getName() {
        return name;
    }

    public void playWith(Team<T> team) {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            System.out.println("Победила команда " + this.getName());
        } else {
            System.out.println("Победила команда " + team.getName());
        }
    }

}
