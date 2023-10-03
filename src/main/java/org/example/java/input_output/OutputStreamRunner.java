package org.example.java.input_output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class OutputStreamRunner {
    public static void main(String[] args) throws IOException {
        File file = Path.of("src", "main", "resources", "textfile.txt").toFile();

        /** Writer - текстовая запись в файл  */
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file, true)) ) {   // параметр append - если false - файл перезатрется, если true - добавится в конец файла
            fileWriter.write("Новая строка из метода write");
            fileWriter.newLine();       // перевод строки, независимый от типа ОС
            fileWriter.append("Новая строка из метода append");
            fileWriter.newLine();       // перевод строки, независимый от типа ОС
        }


        /** FileOutputStream - побайтовая запись в файл */
//        try (BufferedOutputStream fileOutputStream = new BufferedOutputStream(new FileOutputStream(file, true))) {    // параметр append - если false - файл перезатрется, если true - добавится в конец файла
//            fileOutputStream.write("Новая строка".getBytes());
//            fileOutputStream.write(System.lineSeparator().getBytes());  // перевод строки, независимый от типа ОС
//        }
    }
}
