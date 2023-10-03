package org.example.java.input_output.tasks;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class Task5 {
    /**
     * Задан файл в java кодом. Записать его текст в другой файл построчно в обратном порядке
     */

    public static void main(String[] args) throws IOException {
        Path file = Path.of("src", "main", "java", "org", "example", "input_output", "tasks", "Task3.java");
        Path resultFile = Path.of("src", "main", "resources", "Task3Reverse.java");

        try (Stream<String> lines = Files.lines(file);
             BufferedWriter writer = Files.newBufferedWriter(resultFile, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            lines.map(x -> new StringBuilder(x))
                    .map(x -> x.reverse())
                    .forEach(x -> {
                        try {
                            writer.write(x.toString() + System.lineSeparator());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
