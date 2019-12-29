package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.jpa.querydsl.domain.QUser.user;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<User> findByName(String name) {
        return queryFactory.selectFrom(user)
                .where(user.name.eq(name).and(user.name.contains("")))
                .fetch();
    }

    // TODO: 2019-12-29 Change And -> Or
    @Override
    public List<User> findDynamicSearch(String name, String location, Integer age) {
        return queryFactory.selectFrom(user)
                .where(containsName(name),
                        containsLocation(location),
                        eqAge(age))
                .fetch();
    }

    private BooleanExpression containsName(String name) {
        return StringUtils.isEmpty(name) ? null : user.name.containsIgnoreCase(name);
    }

    private BooleanExpression containsLocation(String location) {
        return StringUtils.isEmpty(location) ? null : user.location.containsIgnoreCase(location);
    }

    private BooleanExpression eqAge(Integer age) {
        return age == null ? null : user.age.eq(age);
    }
}
