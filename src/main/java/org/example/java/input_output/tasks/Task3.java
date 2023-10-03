package org.example.java.input_output.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Task3 {
    /**
     * Задан файл с текстом. В каждой строке найти и вывести в консоль наибольшее кол-во цифр, идущих подряд
     */
    public static void main(String[] args) throws IOException {

        Path file = Path.of("src", "main", "resources", "textfile.txt");

        List<String> strings = Files.readAllLines(file);
        strings.stream()
                .map(Task3::findMaxDigitsCounts)
//                .mapToInt(x -> x.intValue())
//                .max()
//                .ifPresent(System.out::println);
                .forEach(System.out::println);
    }

    private static Integer findMaxDigitsCounts(String line) {
        int result = 0;
        int counter = 0;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                counter++;
            }
        }
        if (counter > result) {
            result = counter;
        }
        return result;
    }
}
