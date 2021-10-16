package com.fastcampus.bookmanager.service;

import com.fastcampus.bookmanager.domain.entity.Author;
import com.fastcampus.bookmanager.domain.entity.Book;
import com.fastcampus.bookmanager.repository.AuthorRepository;
import com.fastcampus.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

//    @Transactional
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);


        //Transactional이 있으면 위의 save동작이 완료되지 않는다.
        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않습니다.");
    }
}
