package org.example.java.input_output;

import java.io.File;
import java.io.IOException;

public class FileRunner {
    public static void main(String[] args) {

        File file = new File("in1.txt");
        System.out.println(file.exists());  // существует файл ?
        System.out.println(file.isFile());  // объект -файл ?
        System.out.println(file.isDirectory()); // объект - директория ?

        if (!file.exists()) {   // если фала нет - он создастся
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getAbsoluteFile()); // абсолютный путь от диска 'С'
        System.out.println(file.getPath()); // относительный путь от папки проекта (untitled)
        System.out.println(file.canExecute());  // можем ли выполнить (скрипты, exe-шники), связано с правами доступа
        System.out.println(file.canRead());     // можем ли читать из файла, связано с правами доступа
        System.out.println(file.canWrite());    // можем ли записывать в файл, связано с правами доступа
        File dir = new File("src/main/java/org/example/streams");
        System.out.println(dir.exists());   // существует директория ?
        for (File f : dir.listFiles()) {    // вывод всех файлов из директории
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
    }
}
