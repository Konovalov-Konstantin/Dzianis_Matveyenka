package org.example.java.lambdas;

import java.util.ArrayList;
import java.util.function.Supplier;

public class SupplierInterface {

    public static ArrayList<Car> createCars(Supplier<Car> carSupplier){     // Supplier<T> ничего не принимает и возвращает объект типа T в методе get().
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Car car = carSupplier.get();
            cars.add(car);
        }
        return cars;
    }

    public static void main(String[] args) {
        ArrayList<Car> mycars = createCars( () -> new Car("BMW", "red") );
        System.out.println(mycars);
    }

}

class Car{
    String model;
    String color;

    public Car(String model, String color) {
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "model='" + model + '\'' +
                ", color='" + color + '\'';
    }
}
