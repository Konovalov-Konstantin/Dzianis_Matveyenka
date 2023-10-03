package org.example.java.reflection.task;

@Table(schema = "garage", table = "car")
public class Car {

    @Column(column = "AUDI")
    private String brand;

    @Column(column = "A-4")
    private String model;

//    public Car(String brand, String model) {
//        this.brand = brand;
//        this.model = model;
//    }

}

