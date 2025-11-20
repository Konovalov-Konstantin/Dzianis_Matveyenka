package org.example.database.repository;

import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(String firstname, String lastName, LocalDate localDate);

    List<UserDto> findAllByCompanyAndRole(Integer companyId, Role role);

}
