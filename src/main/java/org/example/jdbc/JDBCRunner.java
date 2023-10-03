package org.example.jdbc;

import java.sql.*;

public class JDBCRunner {

    public static void main(String[] args) throws SQLException {

        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()
        ) {
            /** Метод execute используется для DDL-запросов (создание таблиц, схем...), SELECT запросов и возвращает true, если БД возвращает результат  */
//            boolean execute = statement.execute(

//                "SELECT * FROM airport"
//            );
            /** Метод executeUpdate используется для INSERT, UPDATE, DELETE запросов и возвращает кол-во вставленных, обновленных или удаленных строк  */
//            int i = statement.executeUpdate("INSERT INTO airport (code, country, city) VALUES ('1', 'Russia', 'Surgut')");
//            int executeUpdate = statement.executeUpdate("INSERT INTO aircraft VALUES (1,'Tupolev', NULL)");
//            System.out.println(executeUpdate);


            /** Метод executeQuery используется для SELECT-запрсов, возвращает resultset, из которого нужно доставать каждое значение */
//            ResultSet resultSet = statement.executeQuery("select * from airport");
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("code"));
//                System.out.println(resultSet.getString("country"));
//                System.out.println(resultSet.getString("city"));
//            }

            /** Метод PreparedStatement безопасен для SQL-injection */
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO airport (code, country, city) VALUES (?,?,?)");
//            preparedStatement.setString(1, "89");
//            preparedStatement.setString(2, "Russia");
//            preparedStatement.setString(3, "Noyabrsk");
//            preparedStatement.executeUpdate();

            /** Fetch запросы - получение данных из БД порционно */
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM airport");
//            preparedStatement.setFetchSize(50); // данные будут разбиваться порционно по 50 шт.
//            preparedStatement.setQueryTimeout(10);  // таймаут на 10 сек - если данных очень много, запрос может выполняться долго
//            preparedStatement.setMaxRows(100);  // если результат запроса большой - устанавливает лимит для результата
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                System.out.print(resultSet.getString("code") + "- ");
//                System.out.print(resultSet.getString("country") + "- ");
//                System.out.println(resultSet.getString("city"));
//            }
        }
    }
}
