package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.Company;
import org.example.database.entity.User;
import org.example.database.repository.CompanyRepository;
import org.example.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        companyRepository.findById(object.getCompanyId())
                .ifPresent(user::setCompany);
        return user;
    }

    @Override
    public User map(UserCreateEditDto from, User to) {
        copy(from, to);
        return to;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getName());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthDate(object.getBirthDate());
    }
}
