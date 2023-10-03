package org.example.java.input_output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesClass {
    public static void main(String[] args) throws IOException {

        /** Класс Files самый удобный из (InputStream, OutputStream, File, BufferedReader), т.к. содержит все их методы
         и сам закрывает поток (если возращает не стрим) */

//        Files.copy() - копирует содержимое одного файла в другой файл или в поток
//        Files.createFile() - создает файл
//        Files.createDirectory() - создает папку
//        Files.createTempDirectory() - создает временную директорию, которая удаляется со временем
//        Files.exists() - проверяет, существует ли файл
//        Files.lines() - возвращает Stream из содержимого файла
//        Files.list() - вернет все папки и файлы в указанной директории
//        Files.move() - переместит файл в другую директорию
//        Files.newBufferedReader();  Files.newBufferedWriter() - создание буферизированных потоков ввода / вывода
//        Files.newInputStream(); Files.newOutputStream() - создание потока ввода / вывода
//        Files.readAllBytes(); Files.readAllLines() - прочитать содержимое файла
//        Files.readString() - читает весь файл в одну строку

        /** Запись в файл */
        Path file = Path.of("src", "main", "resources", "textfile.txt");
        Files.write(file, List.of("Новая строка из класса Files"), StandardOpenOption.APPEND); // StandardOpenOption.APPEND - добавит в конец файла, а не затрет файл

        /** Считывание файла */
        try (Stream<String> lines = Files.lines(file)) {    // чтение всех строк из файла в виде потока
            System.out.println(lines.collect(Collectors.joining(System.lineSeparator())));
        }

    }
}
