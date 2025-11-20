package org.example.database.repository;

import org.example.database.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(String firstname, String lastName, LocalDate localDate);

}
