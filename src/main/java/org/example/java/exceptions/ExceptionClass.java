package org.example.java.exceptions;

import java.util.Scanner;

public class ExceptionClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            try {
                System.out.println(a / b);
            } catch (ArithmeticException e) {
                System.out.println("ВВЕДИТЕ ЧИСЛО, ОТЛИЧНОЕ ОТ НУЛЯ");
            }
        }

    }
}