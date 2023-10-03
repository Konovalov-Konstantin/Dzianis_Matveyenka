package org.example.java.lambdas;

import java.util.List;
import java.util.function.Function;

public class FunctionInterface {

    public static void main(String[] args) {
        List<Dog> dogs = List.of(new Dog("Bob", 3), new Dog("Pech", 2), new Dog("Carl", 5));
        Double result = avgOfAge(dogs, dog -> (double)dog.age);
        System.out.println("result = " + result);
    }

    public static Double avgOfAge(List<Dog> dogs, Function<Dog, Double> f) {
        double result = 0;
        for (Dog dog : dogs) {
            result += f.apply(dog);
        }
        return result / dogs.size();
    }

}

class Dog {
    String name;
    int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", age=" + age;
    }
}
