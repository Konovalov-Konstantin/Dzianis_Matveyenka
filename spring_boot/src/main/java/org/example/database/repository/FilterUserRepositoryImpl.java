package org.example.database.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.example.database.entity.QUser;
import org.example.database.entity.User;
import org.example.database.querydsl.QPredicates;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

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
}
