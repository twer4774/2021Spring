package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {


    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    public void reviewTest(){
//        List<Review> reviews = reviewRepository.findAll();

//        System.out.println(reviews);

//        List<Review> reviews = reviewRepository.findAllByFetchJoin();

//        System.out.println("전체를 가져왔습니다.");
//        System.out.println(reviews.get(0).getComments());

//        System.out.println("첫 번째 리뷰의 코멘트들을 가져왔습니다.");

        List<Review> reviews = reviewRepository.findAllByEntityGraph();

        reviews.forEach(System.out::println);

    }
}
