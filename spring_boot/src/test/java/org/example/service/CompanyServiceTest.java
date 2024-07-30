package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.DatabaseProperties;
import org.example.dto.CompanyReadDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")   // активируется профиль test и считывается application-test.yml, переопределяя конфиги с теми же ключами, что и в application.yml основного приложения
@RequiredArgsConstructor    // чтобы не использовать @Autowired над полями
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // чтобы не использовать @Autowired над полями
class CompanyServiceTest {

    private static final Integer COMPANY_ID = 1;

    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById(){
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());

        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));
    }

}