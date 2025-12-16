package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants // создает статические константы полей класса
public class UserCreateEditDto {
    private String name;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    private Integer companyId;
}
