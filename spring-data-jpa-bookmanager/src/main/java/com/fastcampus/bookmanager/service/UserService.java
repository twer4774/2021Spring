package com.fastcampus.bookmanager.service;

import com.fastcampus.bookmanager.domain.entity.User;
import com.fastcampus.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

//    @Autowired
//    private UserRepository userRepository;

    @Transactional
    public void put(){
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com");
        //위까지는 비영속상태

        //영속상태
        //userRepository.save()와 동일한 기능
        entityManager.persist(user);

        //remove는 영속상태일때만 가능
//        entityManager.remove(user);

        //준영속상태 - repository에서는 제공되지 않는 entityManager에서만 사용 가능
        entityManager.detach(user);


        user.setName("newUserAfterPersist");
        entityManager.merge(user); //준영속상태에서도 DB에 반영할 때 사용

        entityManager.clear();

    }
}
