package org.example.java.input_output.tasks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Task1 {
    /**
     * Задан файл с текстом. Найти и вывести в консоль все слова, начинающиеся с гласной буквы
     */
    public static void main(String[] args) throws IOException {

        String VOWELS = "аеёыуоэяию";

        Path file = Path.of("src", "main", "resources", "textfile.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (VOWELS.contains(next.toLowerCase().substring(0, 1))) {
                    System.out.println(next);
                }
            }
        }
    }
}
