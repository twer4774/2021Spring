package com.fastcampus.bookmanager.domain.listener;

import com.fastcampus.bookmanager.domain.entity.User;
import com.fastcampus.bookmanager.domain.entity.UserHistory;
import com.fastcampus.bookmanager.repository.UserHistoryRepository;
import com.fastcampus.bookmanager.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();

//        userHistory.setUserId(user.getId());
        userHistory.setUser(user);
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());

        userHistoryRepository.save(userHistory);
    }

}
