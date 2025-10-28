package org.example.database.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Company {
    private final Integer id;
    private final String name;
}
