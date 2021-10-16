package com.fastcampus.bookmanager.service;

import com.fastcampus.bookmanager.domain.entity.User;
import com.fastcampus.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;


    @Test
    void entityManagerTest(){
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    void cacheFindTest(){
        //1차 캐시는 id값으로 조회했을 경우만 적용된다. => 선결조건 : transactional
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());

        //1차 캐시가 적용되지 않아 매번 쿼리문이 나온다.
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
    }

    @Test
    void cacheFindTest2(){
        User user = userRepository.findById(1L).get();
        user.setName("marrrrr");

        userRepository.save(user);

        userRepository.flush(); //동기화

        System.out.println("------------------");

        user.setEmail("marrrrrtin@fastcampus.com");
        userRepository.save(user);

        userRepository.flush();
    }
}
