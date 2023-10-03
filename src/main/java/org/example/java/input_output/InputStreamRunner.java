package org.example.java.input_output;

import java.io.*;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class InputStreamRunner {
    public static void main(String[] args) throws IOException {

        // 1 вариант указания пути к файлу
        // File.separator позволяет избежать ошибок  в зависимости от ОС, т.к. в разных ОС используются разные разделители в пути к файлу
        //File file = new File(String.join(File.separator, "resources", "textfile.txt"));

        // 2 вариант указания пути к файлу (от папки проекта ('untitled'))
        File file = Path.of("src", "main", "resources", "textfile.txt").toFile();

        /** InputStream предназначен для работы с байтами, Reader - для работы с текстом */

        /** Reader */
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String collect = reader.lines()
                    .collect(Collectors.joining("\n"));
            System.out.println(collect);
        }


        /** InputStream  */
        try (FileInputStream fileInputStream = new FileInputStream(file);) {
            /** считывание всего файла сразу */
//            byte[] bytes = fileInputStream.readAllBytes();
//            String s = new String(bytes);
//            System.out.println(s);

            /** считывание по 1 байту */
//            byte[] bytes = new byte[fileInputStream.available()];
//            int byteCounter = 0;
//            byte currentByte;
//            while ((currentByte = (byte) fileInputStream.read()) != -1) {
//                bytes[byteCounter++] = currentByte;
//                String s = new String(bytes);
//                System.out.println(s);
//            }
        }
    }
}
