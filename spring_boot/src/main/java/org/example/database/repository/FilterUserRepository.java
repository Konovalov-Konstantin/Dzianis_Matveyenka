package org.example.database.repository;

import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.dto.UserReadDto;

import java.time.LocalDate;
import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(String firstname, String lastName, LocalDate localDate);

    List<UserReadDto> findAllByCompanyAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

}
