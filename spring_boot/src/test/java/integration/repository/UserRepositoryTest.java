package integration.repository;

import lombok.RequiredArgsConstructor;
import org.example.Application;
import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.database.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void updateRoleTest() {
        User user = userRepository.getById(4L);
        assertThat(user.getRole()).isEqualTo(Role.USER);

        userRepository.updateRole(Role.ADMIN, 4L);
        User updatedUser = userRepository.getById(4L);
        assertThat(updatedUser.getRole()).isEqualTo(Role.ADMIN);
    }

    @Test
    void findTop3ByBirthDateBeforeTest() {
        // для динамической сортировки
        // 1. вариант сортера с хардкодом
        // Sort sortByLastNameAndFirstName = Sort.by("lastname").and(Sort.by("firstname"));
        // 2. безопасный вариант сортера
        Sort.TypedSort<User> sortBy = Sort.sort(User.class);
        Sort sortByLastNameAndFirstName = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        List<User> top3ByBirthDateBeforeUsers = userRepository.findTop3ByBirthDateBefore(
                LocalDate.now(), sortByLastNameAndFirstName);

        assertThat(top3ByBirthDateBeforeUsers).hasSize(3);
        assertEquals("Ivanov", top3ByBirthDateBeforeUsers.get(0).getLastname());
    }

    @Test
    void checkPagebleTest() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        List<User> users = userRepository.findAllBy(pageable);
        assertThat(users).hasSize(2);
        assertEquals(3, users.get(0).getId());
        assertEquals(4, users.get(1).getId());
    }

    @Test
    void findPageByTest() {
        PageRequest pageable = PageRequest.of(1, 2, Sort.by("id"));
        Page<User> userPage = userRepository.findPageBy(pageable);
        userPage.forEach(System.out::println);
        assertThat(userPage).hasSize(2); // элементы 3 и4

        while (userPage.hasNext()) {   // Page позволяет итерироваться по выборкам из БД
            userPage = userRepository.findPageBy(userPage.nextPageable());
            userPage.forEach(System.out::println);
            assertThat(userPage).hasSize(1);   //  элемент 5
        }
    }
}