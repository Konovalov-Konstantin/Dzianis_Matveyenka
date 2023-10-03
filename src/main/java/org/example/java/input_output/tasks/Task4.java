package org.example.java.input_output.tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task4 {
    /** Задан файл в java кодом. Все слова public заменить на private. Результат сохранить в другой файл */

    public static void main(String[] args) throws IOException {
        Path file = Path.of("src", "main", "java", "org", "example", "input_output", "tasks", "Task3.java");
        String s = Files.readString(file);  // читает весь файл в одну строку
        String newString = s.replace("public", "private");
        Path resultFile = Path.of("src", "main", "resources", "Task3Change.java");
        Files.writeString(resultFile, newString);

    }


}
