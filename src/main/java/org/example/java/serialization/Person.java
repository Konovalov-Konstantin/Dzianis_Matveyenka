package org.example.java.serialization;

import java.io.Serializable;

public class Person implements Serializable {
    /** Если поле serialVersionUID будет заполняться по умолчанию,
     * то при изменении класса и попытке десериализации упадет исключение InvalidClassException
     * еали указать это поле самостоятельно, исключения не будет. Поле serialVersionUID должно быть статическим, т.к.
     * статика и поля transient не учитываются при сериализации */

    public static final long serialVersionUID = 1L;

    private int age;
    private String name;

    private transient int somethingTransient = 10;  // transient поле не будет учиваться при сериализации и не будет сохранено
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
