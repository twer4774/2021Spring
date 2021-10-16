package com.fastcampus.bookmanager.domain;

import com.fastcampus.bookmanager.domain.entity.User;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    public void test(){
        User user= new User();
        user.setEmail("aa@mail.com");
        user.setName("aaa");

        System.out.println(user);
    }
}