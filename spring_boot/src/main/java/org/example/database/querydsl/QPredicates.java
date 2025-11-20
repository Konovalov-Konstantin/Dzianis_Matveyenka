package org.example.database.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QPredicates {

    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicates builder() {
        return new QPredicates();
    }

    public <T> QPredicates add(T obj, Function<T, Predicate> function) {
        if (obj != null) {
            predicates.add(function.apply(obj));
        }
        return this;
    }

    public Predicate buildAndPredicate() {
        return ExpressionUtils.allOf(predicates);   // все предикаты соединяются через логическое "И"
    }

    public Predicate buildOrPredicate() {
        return ExpressionUtils.anyOf(predicates);   // все предикаты соединяются через логическое "ИЛИ"
    }

}
