package org.example.database.repository;

import org.example.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // примеры методов с ручным составлением SQL-запросов
    // если параметр nativeQuery в аннотации @Query false - тогда запрос пишется в HQL, если в true - тогдв в SQL

    // пример с HQL
    @Query("select u from User u where u.firstname like :firstName and u.lastname like :lastName")
    List<User> findAllBy(String firstName, String lastName);

    // пример с SQL
    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findAllByUserName(String username);
}
