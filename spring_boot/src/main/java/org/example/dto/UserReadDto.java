package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class UserReadDto {
    Long id;
    String firstname;
    String lastname;
    LocalDate birthDate;
    CompanyReadDto company;
}
