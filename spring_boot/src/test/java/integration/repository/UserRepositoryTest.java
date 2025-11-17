package integration.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.Application;
import org.example.database.entity.User;
import org.example.database.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@Transactional
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Test
    void findAllByFirstNameAndLastNameHQLTest() {
        List<User> users = userRepository.findAllBy("%a%", "%ov%");
        assertThat(users).hasSize(3);
    }

    @Test
    void findAllByUserNameTest() {
        List<User> allByUserName = userRepository.findAllByUserName("petr@gmail.com");
        assertThat(allByUserName).hasSize(1);
    }
}