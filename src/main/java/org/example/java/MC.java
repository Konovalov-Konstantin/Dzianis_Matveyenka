package org.example.java;

public class MC {

    public static void load(MyInterface... computers) {
        for (MyInterface computer : computers) {
            computer.doSmth();
            computer.printDefault();
            if (computer instanceof Laptop) {
                ((Laptop) computer).printsmth();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        load(new Computer(), new Laptop(), new Computer());
        WeekDays weekDay = WeekDays.SUNDAY;
        System.out.println(weekDay);
    }
}
