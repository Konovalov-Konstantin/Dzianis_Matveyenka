package org.example.mapper;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.User;
import org.example.dto.UserReadDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getFirstname(),
                object.getLastname(),
                object.getBirthDate(),
                Optional.ofNullable(object.getCompany())
                        .map(companyReadMapper::map)
                        .orElse(null)
        );
    }
}
