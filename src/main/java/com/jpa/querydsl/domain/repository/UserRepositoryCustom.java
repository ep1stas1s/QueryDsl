package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByName(String name);

    List<User> findDynamicSearch(String name, String location, Integer age);
}
