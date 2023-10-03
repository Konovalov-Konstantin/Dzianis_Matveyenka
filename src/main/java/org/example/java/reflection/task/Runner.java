package org.example.java.reflection.task;

/** Создать класс Car с полями String brand и String model. Создать аннотации @Table (принимает название схемы
    и таблицы в БД) и @Column (принимает название колонки в БД). Пометить класс аннотацией @Table и поля
    аннотацией @Column. Написать программу, принимающуюу объект класса Car с проинициализированными полями
    и составляющую запрос "INSERT" в виде строкина основании данных объекта  **/

public class Runner {
    public static void main(String[] args) {
        Car audi = new Car();
        System.out.println(sqlCreator(audi));
    }

    public static String sqlCreator(Car car) {
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        try {
            String schema = car.getClass()
                    .getAnnotation(Table.class).schema();
            sb.append(schema);
            sb.append(".");
            String table = car.getClass()
                    .getAnnotation(Table.class).table();
            sb.append(table);
            sb.append(" (model, brand) VALUES ('");
            String brand = car.getClass().getDeclaredField("brand")
                    .getAnnotation(Column.class).column();
            sb.append(brand);
            sb.append("','");
            String model = car.getClass().getDeclaredField("model")
                    .getAnnotation(Column.class).column();
            sb.append(model);
            sb.append("');");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
