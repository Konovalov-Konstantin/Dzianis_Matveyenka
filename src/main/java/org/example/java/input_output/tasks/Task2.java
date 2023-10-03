package org.example.java.input_output.tasks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Task2 {
    /**
     * Задан файл с текстом. Найти и вывести в консоль все слова, для которых последняя буква одного слова совпадает с первой буквой следующего слова
     */
    public static void main(String[] args) throws IOException {

        Path file = Path.of("src", "main", "resources", "textfile.txt");

        try (Scanner scanner = new Scanner(file)) {
            String firstWord = null;
            if (scanner.hasNext()) {
                firstWord = scanner.next();
            }
            while (scanner.hasNext()) {
                String secondWord = scanner.next();
                if (firstWord.toLowerCase().charAt(firstWord.length() - 1) == secondWord.toLowerCase().charAt(0)) {
                    System.out.println(firstWord + " " + secondWord);
                }
                firstWord = secondWord;
            }
        }
    }
}
