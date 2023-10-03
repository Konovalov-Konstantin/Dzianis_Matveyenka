package org.example.java.shift;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MyClass {
    static String sortRezim = "up";

    public static void main(String[] args) {
//        File input1 = Path.of(args[]);
        checkParams(args);

    }

    private static void checkParams(String[] args) {
        List<String> params = List.of(args);


        String dataType;
        String outFile;
        int count = 0;

        if (params.get(count).equals("-a") || params.get(count).equals("-d")) {
            if (params.get(count).equals("-d")) sortRezim = "down";
            count++;
        }
        System.out.println("sortRezim = " + sortRezim);


        dataType = params.get(count++);
        System.out.println("dataType = " + dataType);



        outFile = params.get(count++);
        System.out.println("outFile = " + outFile);

        List<String> inputFiles = new ArrayList<>();

        for (int i = count; i < params.size(); i++) {
            inputFiles.add(params.get(count++));
        }
        System.out.println("Входные файлы:");
        inputFiles.stream().forEach(System.out::println);

//        List<List<String>> listOfArrays = new ArrayList<>(inputFiles.size());

        for (String inputFile : inputFiles) {
            File file = Path.of(inputFile).toFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file), 500)) {
                String s;
                List<String> input = new ArrayList<>();
                while ((s = reader.readLine()) != null) {
                    System.out.println(s);
                    input.add(s);
                }
//                listOfArrays.add(input);    // добавляем лист в список листов
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }


}
