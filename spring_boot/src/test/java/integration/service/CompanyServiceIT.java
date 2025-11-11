package integration.service;

import lombok.RequiredArgsConstructor;
import org.example.Application;
import org.example.config.DatabaseProperties;
import org.example.dto.CompanyReadDto;
import org.example.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")   // активируется профиль test и считывается application-test.yml, переопределяя конфиги с теми же ключами, что и в application.yml основного приложения
@RequiredArgsConstructor    // чтобы не использовать @Autowired над полями
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)   // чтобы не использовать @Autowired над полями

//@ExtendWith(SpringExtension.class)  // SpringExtension предоставляет спринг-контекст
//@ContextConfiguration(
//        classes = Application.class,        // указываем какой конкретно контекст нужно использовать в тесте
//        initializers = ConfigDataApplicationContextInitializer.class    // сканирует .yml файлы с конфигами
//)
//@TestPropertySource("classpath:application-test.yml")  // аннотация работает только с .properties файлами, с .yml не работает (см.initializers в аннотации @ContextConfiguration выше)
public class CompanyServiceIT {

    private static final Integer COMPANY_ID = 1;
    private final CompanyService companyService;
    private final DatabaseProperties databaseProperties;

    @Test
    void findById() {
        Optional<CompanyReadDto> actualResult = companyService.findById(COMPANY_ID);

        assertTrue(actualResult.isPresent());
        CompanyReadDto expectedResult = new CompanyReadDto(COMPANY_ID);
        actualResult.ifPresent(actual -> assertEquals(expectedResult, actual));

        assertEquals("test", databaseProperties.getUsername());     // т.к. активирован профиль 'test' @ActiveProfiles("test") - username должен тянуться из application-test.yml
        assertEquals("test", databaseProperties.getPassword());
    }

}
