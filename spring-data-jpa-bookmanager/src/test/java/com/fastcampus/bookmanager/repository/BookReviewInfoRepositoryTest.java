package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Book;
import com.fastcampus.bookmanager.domain.entity.BookReviewInfo;
import com.fastcampus.bookmanager.domain.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BookReviewInfoRepositoryTest {


    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;


    @Test
    public void crud(){

        givenBookReviewInfo();

        System.out.println(bookReviewInfoRepository.findAll());


    }

    //실제로는 불필요한 테스트 -findById를 넉넉한 숫자로 넣어서 전체테스트 실행시 성공함
//    @Test
//    public void crud2(){
//
//        givenBookReviewInfo();
//
//        Book result =
//                bookReviewInfoRepository
//                        .findById(7L)
//                        .orElseThrow(RuntimeException::new)
//                        .getBook();
//
//        System.out.println("result : " + result);
//
//        BookReviewInfo result2 = bookRepository
//                .findById(7L)
//                .orElseThrow(RuntimeException::new)
//                .getBookReviewInfo();
//
//        System.out.println("result2 : " + result2);
//    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("package");
        book.setAuthorId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setReviewCount(2);
        bookReviewInfo.setAverageReviewScore(4.5f);

        bookReviewInfoRepository.save(bookReviewInfo);
    }

}