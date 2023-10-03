package org.example.java.serialization;

import java.io.*;
import java.nio.file.Path;

public class SerializationDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /** Сериализация позволяет сохранить объект и его состояние в виде последовательности байт на жесткий диск или передать
         по почте или в другое приложение и потом восстановить его из этой последовательности байт
         Класс этого объекта должен реализовывать интерфейс Serializable или Externalizable,
         иначе при сохранении будет ошибка NotSerializableException */

        Path file = Path.of("src", "main", "java", "org", "example", "serialization", "fileserialization.txt");
        /** Сериализация - сохранение объекта в файл */
//        savePerson(file);
        /** Десериализация - восстановление объекта из файла */
        loadPerson(file);

    }

    private static void loadPerson(Path file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            Person person = (Person) objectInputStream.readObject();
            System.out.println(person);
        }
    }

    private static void savePerson(Path file) throws IOException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            Person Nikolay = new Person(26, "Nikolay");
            outputStream.writeObject(Nikolay);
        }
    }
}
