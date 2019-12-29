package com.jpa.querydsl.domain.repository;

import com.jpa.querydsl.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRepositorySupportTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByName() {
        User whale = User.builder().name("whale").build();
        User duck = User.builder().name("duck").build();
        userRepository.save(whale);
        userRepository.save(duck);

        List<User> result = userRepository.findByName("whale");

        System.out.println(result);
    }

    @Test
    void findDynamicSearch() {
        User whale = User.builder().name("whale").age(28).location("청담").build();
        User duck = User.builder().name("duck").age(28).location("신림").build();
        userRepository.save(whale);
        userRepository.save(duck);

        List<User> dynamicSearch = userRepository.findDynamicSearch("", "신림", null);

        assertThat(dynamicSearch.size()).isEqualTo(1);
    }
}