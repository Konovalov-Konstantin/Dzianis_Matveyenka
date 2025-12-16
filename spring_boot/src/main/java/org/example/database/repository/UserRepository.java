package org.example.database.repository;

import com.querydsl.core.types.Predicate;
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
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends
        JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User>
{

    // примеры методов с ручным составлением SQL-запросов
    // если параметр nativeQuery в аннотации @Query false - тогда запрос пишется в HQL, если в true - тогдв в SQL

    // пример с HQL
    @Query("select u from User u where u.firstname like :firstName and u.lastname like :lastName")
    List<User> findAllBy(@Param("firstName") String firstName, @Param("lastName") String lastName);

    // пример с SQL
    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username", nativeQuery = true)
    List<User> findAllByUserName(@Param("username") String username);

    @Modifying(clearAutomatically = true)  // без этой аннотации при изменении данных в БД упадем с ошибкой
                                           // clearAutomatically - удалет устаревшие данные из кэша после обновлении данных в БД
    @Query("update User u set u.role = :role WHERE u.id in (:ids)")
    int updateRole(@Param("role") Role role, @Param("ids") Long... ids);

    // динамическа сортировка (см. тест findTop3ByBirthDateBeforeTest)
    @Lock(LockModeType.OPTIMISTIC)  // уровень блокировки данных. Для обнаружения конфликтов в entity используется доп.поле с аннотацией @Version
    List<User> findTop3ByBirthDateBefore(LocalDate birthDate, Sort sort);   // вернет не более 3х User-ов

    // запрос с пагинацией результата (см тест checkPagebleTest)
    List<User> findAllBy(Pageable pageable);

    // Page - подсписок списка объектов. Можно установить лимит на объектов на странице, номер страницы.
    // Есть данные об общем кол-ве страниц (см тест findPageByTest)
    Page<User> findPageBy(Pageable pageable);

    /** querydsl */
    @Override
    Iterable<User> findAll(Predicate predicate);
}
