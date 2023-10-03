package org.example.java.lambdas;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerInterface {
    public static void changeCat(Cat cat, Consumer<Cat> catConsumer) {  // Consumer<T> принимает объект типа <T> и ничего не возвращает
        catConsumer.accept(cat);
    }

    public static void main(String[] args) {
        Cat cat = new Cat("Basik", 3);
        System.out.println(cat);

        changeCat(cat, c -> {
            c.name = "Borsh";
            c.age = 4;
        });

        System.out.println(cat);


        /**
         *  forEach принимает в параметры тип Consumer<T>
         */
        List<Cat> myCats = List.of(new Cat("Bob", 2), new Cat("Tom", 4), new Cat("George", 3));
        myCats.forEach(k -> {
            System.out.println(k);  // forEach принимает в параметры тип Consumer<T>
            k.age *= 2;
            System.out.println(k);
        });
    }

}

class Cat {
    String name;
    int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age;
    }
}
