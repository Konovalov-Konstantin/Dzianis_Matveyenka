package integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.Application;
import org.example.database.entity.Company;
import org.example.database.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class CompanyRepositoryTest {

    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void findById() {
        Company company = entityManager.find(Company.class, 1);
        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void delete() {
        companyRepository.save(new Company(4, "Test", Collections.emptyMap()));
        Optional<Company> company = companyRepository.findById(4);
        assertNotNull(company);
        company.ifPresent(companyRepository::delete);
        entityManager.flush();
        Optional<Company> mustBeNullCompany = companyRepository.findById(4);
        assertTrue(mustBeNullCompany.isEmpty());
    }

    @Test
    void checkFindByQueries() {
        Optional<Company> google = companyRepository.findByName("Google");
        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase("mazon");
        assertNotNull(google);
        assertThat(companies).hasSize(1);
    }

    @Test
    void checkFindByNameQuery() {
        Optional<Company> company = companyRepository.findByNameQuery("Amazon");
        assertNotNull(company);

        company.map(c -> c.getLocales().values())
                .ifPresent(x -> assertEquals(2, x.size()));
    }
}