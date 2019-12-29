package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jpa.querydsl.domain.QUser.*;


@Repository
public class UserRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory queryFactory;

    public UserRepositorySupport(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }

    public List<User> findByName(String name) {
        return queryFactory.selectFrom(user)
                .where(user.name.eq(name))
                .fetch();
    }
}
