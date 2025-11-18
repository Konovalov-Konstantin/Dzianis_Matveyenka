package org.example.database.repository;

import jakarta.persistence.LockModeType;
import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // примеры методов с ручным составлением SQL-запросов
    // если параметр nativeQuery в аннотации @Query false - тогда запрос пишется в HQL, если в true - тогдв в SQL

    // пример с HQL
    @Query("select u from User u where u.firstname like :firstName and u.lastname like :lastName")
    List<User> findAllBy(String firstName, String lastName);

    // пример с SQL
    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findAllByUserName(String username);

    @Modifying(clearAutomatically = true)  // без этой аннотации при изменении данных в БД упадем с ошибкой
                                           // clearAutomatically - удалет устаревшие данные из кэша после обновлении данных в БД
    @Query("update User u set u.role = :role WHERE u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    // динамическа сортировка (см. тест findTop3ByBirthDateBeforeTest)
    @Lock(LockModeType.OPTIMISTIC)  // уровень блокировки данных. Для обнаружения конфликтов в entity используется доп.поле с аннотацией @Version
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);   // вернет не более 3х User-ов

    // запрос с пагинацией результата (см тест checkPagebleTest)
    List<User> findAllBy(Pageable pageable);

    // Page - подсписок списка объектов. Можно установить лимит на объектов на странице, номер страницы.
    // Есть данные об общем кол-ве страниц (см тест findPageByTest)
    Page<User> findPageBy(Pageable pageable);
}
