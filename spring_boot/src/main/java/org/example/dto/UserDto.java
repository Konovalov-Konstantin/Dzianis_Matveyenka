package org.example.dto;

import java.time.LocalDate;

public record UserDto(String firstname,
                      String lastname,
                      LocalDate birthDate) {
}
