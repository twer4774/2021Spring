package com.fastcampus.bookmanager.service;

import com.fastcampus.bookmanager.repository.AuthorRepository;
import com.fastcampus.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void transactionTest(){
        try{
            bookService.putBookAndAuthor();
        } catch (RuntimeException e){

            System.out.println(e.getMessage());
        }

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());

    }
}