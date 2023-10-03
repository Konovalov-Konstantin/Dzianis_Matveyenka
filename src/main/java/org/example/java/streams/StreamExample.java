package org.example.java.streams;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        List<String> strings = List.of("1", "2", "3", "4", "5", "6");

        strings.stream()
                .map((value) -> value + value)  // .map - преобразует один тип (на входе) в другой (на выходе)
                .map(s -> Integer.parseInt(s))  // преоборазует String в Integer
                .filter((m) -> m > 15)
                .sorted((x, y) -> y - x)        // сортировка в обратном порядке
                .skip(1)        // пропустить первый элемент
                .limit(5)   // лимит на 5 элементов
                .forEach(System.out::println);

        // стримы для примитивных типов данных
        // вариант создания №1 (из коллекции объектов)
        Stream.of("1", "2", "3", "4", "5", "6")
                .map(v -> Integer.parseInt(v) * 10)
                .mapToInt(x -> x.intValue())      // преобразование типа Integer в int
                // .mapToDouble(x -> x.doubleValue()) // преобразование типа Integer в double
                // .map(x -> x.longValue())           // преобразование типа Integer в long
                //          *** после преобразования типов в примитивные становятся доступными функции ниже ***
                //  .sum()    // сумма
                //  .average()    // среднее
                //  .max()       // максимум
                //  .count()      // количество
                //  .summaryStatistics()  // статистика (доступны сумма, среднее, кол-во, min, max)
                //  .peek(x-> System.out.println(x))    // нетерминальный метод (можно продолжить цепочку вызовов), принимает Consumer

                //          *** также можно из примитивов сделать массив объектов (Integer, Double, Long...) ***
                .mapToObj(x -> Integer.valueOf(x))
//                .collect(Collectors.toSet())
//                .collect(Collectors.toList()
                .forEach(System.out::println);

        // вариант создания №2
        IntStream intStream = IntStream.of(1, 2, 3, 4, 5, 6);
//        intStream.forEach()...............;

        // Бесконечный цикл от 10 через 3 с лимитом на 10 значений
        System.out.println("Бесконечный цикл от 10 через 3 с лимитом на 10 значений");
        IntStream.iterate(10, operand -> operand + 3)
                .limit(10)
                .forEach(System.out::println);

        // Найти среднее всех нечетных чисел, делящихся на 5.
        System.out.println("среднее всех нечетных чисел, делящихся на 5");
        List.of(3, 1, 15, 17, 16, 52, 55, 37, 75)
                .stream().filter(x -> x % 5 == 0 && x % 2 != 0)
                .mapToInt(x -> x.intValue())
                .average()
                .ifPresent(System.out::println);

        // Найти кол-во уникальных элементов длиной более 8 символов
        System.out.println("кол-во уникальных элементов длиной более 8 символов: ");
        List<String> innerList = List.of("qef", "qeegsvewegwefe1", "wrvsbrs", "weerfsef",
                "wersefeg", "qeegsvewegwefe2", "wrsrbe", "qeegsvewegwefe3");
        System.out.println((long) innerList.stream()
                .filter(x -> x.length() > 8)
                .collect(Collectors.toSet())
                .size());

//                      *** или через distinct ****
//        System.out.println(innerList.stream()
//                .filter(x -> x.length() > 8)
//                .distinct() // удаляет дубликаты
//                .count());


        /** Дана Map<String, Integer>, найти сумму всех значений, длины ключей которых меньше 7 символов **/
        System.out.println("сумма всех значений, длины ключей которых меньше 7 символов:");
        Map<String, Integer> innerMap = Map.of(
                "sgre", 10,
                "sgresrbef", 11,
                "wegseeggae", 12,
                "wegf", 13,
                "rtdsrgsefaf", 14,
                "qf", 15);

        System.out.println(innerMap.entrySet().stream()
                .filter(x -> x.getKey().length() < 7)
                .mapToInt(x -> x.getValue().intValue())
                .sum());

        /** Дан список целых чисел. Вывести строку, представлющую собой конкатенацию строковых предствлений этих чисел **/
        System.out.println("конкатенация строковых предствлений переданных чисел");
        List<Integer> intlist = List.of(1, 5, 3, 4, 8, 6, 4, 2, 6);
        String result = intlist.stream()
                .map(x -> String.valueOf(x))
//                .collect(Collectors.joining()); // joining объединяет все в одну строку
                .collect(Collectors.joining(", ", "Результат: ", " !!!"));  // joining объединяет все в одну строку и добавляет разделитель, префикс в начале и суффикс в конце
        System.out.println(result);

        /** Дан класс Person. Вывести полное имя самого старшего человека, у которого длина полного имени меньше 15 символов **/
        Stream<Person> personStream = Stream.of(
                new Person(15, "Николя", "Саркози"),
                new Person(23, "Андрей", "Укупник"),        // должен вывести его
                new Person(32, "Бидон", "Байденовичус"),    // полное имя длина более 15 символов
                new Person(18, "Урсула", "Ебаляйн")
        );

        personStream
                .filter(x -> x.getFullName().length() < 15)
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(x -> System.out.println(x.getFullName()));
    }
}

class Person {
    int age;
    String firstname;
    String lastname;

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Person(int age, String firstname, String lastname) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}