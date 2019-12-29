package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

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
                .where(user.name.eq(name))
                .fetch();
    }
}
