package integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.Application;
import org.example.database.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class CompanyRepositoryTest {

    private final EntityManager entityManager;

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }
}