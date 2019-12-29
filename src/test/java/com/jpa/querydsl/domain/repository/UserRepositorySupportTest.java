package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositorySupportTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositorySupport userRepositorySupport;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void name() {
        User whale = User.builder().name("whale").build();
        User duck = User.builder().name("duck").build();
        userRepository.save(whale);
        userRepository.save(duck);

        List<User> result = userRepository.findByName("whale");

        System.out.println(result);
    }
}