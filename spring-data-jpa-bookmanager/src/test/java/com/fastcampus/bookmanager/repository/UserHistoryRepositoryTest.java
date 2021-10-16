package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserHistoryRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    public void userHistoryTest(){

        User user = new User();

        user.setEmail("walter@mail.com");
        user.setName("walter");

        userRepository.save(user);

        user.setName("walterew");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }
}