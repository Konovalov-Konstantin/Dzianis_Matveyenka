package org.example.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.example.database.entity.QUser;
import org.example.database.entity.Role;
import org.example.database.entity.User;
import org.example.database.querydsl.QPredicates;
import org.example.dto.CompanyReadDto;
import org.example.dto.UserReadDto;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    /** querydsl */
    @Override
    public List<User> findAllByFilter(String firstname, String lastName, LocalDate localDate) {
        Predicate predicate = QPredicates.builder()
                .add(firstname, QUser.user.firstname::containsIgnoreCase)
                .add(lastName, QUser.user.lastname::containsIgnoreCase)
                .add(localDate, QUser.user.birthDate::before)
                .buildAndPredicate();

        return new JPAQuery<User>(entityManager)
                .select(QUser.user)
                .from(QUser.user)
                .where(predicate)
                .fetch();
    }

    /**  jdbctemplate  */
    @Override
    public List<UserReadDto> findAllByCompanyAndRole(Integer companyId, Role role) {
        // чтоб ide подсказывала при составлении sql - alt+enter - inject language or reference - postgresql
        return jdbcTemplate.query(
                "SELECT firstname, lastname, birth_date FROM users WHERE company_id = ? AND role = ?",
                (rs, rowNum) -> new UserReadDto(
                        rs.getLong("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDate("birth_date").toLocalDate(),
                        rs.getObject("company", CompanyReadDto.class)
                ),
                companyId, role.name()
        );
    }

    /** batch - запрос */
    @Override
    public void updateCompanyAndRole(List<User> users) {
        List<Object[]> args = users.stream()
                .map(user -> new Object[]{
                        user.getCompany().getId(),
                        user.getRole().name(),
                        user.getId()}
                ).toList();

        jdbcTemplate.batchUpdate(
                "UPDATE users SET company_id = ?, role = ? WHERE id = ?",
                      args  // вторым аргументом передается массив размером 3 т.к. 3 вопроса в sql-запросе
                );
    }
}
