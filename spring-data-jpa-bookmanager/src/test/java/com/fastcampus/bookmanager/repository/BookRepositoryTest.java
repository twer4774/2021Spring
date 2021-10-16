package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Book;
import com.fastcampus.bookmanager.domain.entity.Publisher;
import com.fastcampus.bookmanager.domain.entity.Review;
import com.fastcampus.bookmanager.domain.entity.User;
import com.fastcampus.bookmanager.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
//@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void bookTet() {
        Book book = new Book();

        book.setName("패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);


        bookRepository.save(book);


        System.out.println(bookRepository.findAll());
    }

    @Test
    public void bookRelationTest() {
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Reivew : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

    }

    @Test
    public void bookCascadeTest(){
        Book book = new Book();
        book.setName("초격채 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("fastcampus");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("slowcampus");
        bookRepository.save(book1);

        System.out.println("publishers : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);
        bookRepository.save(book3);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers " + publisherRepository.findAll());

    }

    @Test
    public void bookRemoveCascadeTest(){
        bookRepository.deleteById(2L);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    public void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
//        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }


    @Test
    public void queryTest(){
        System.out.println("findByNameRecently : " +
                bookRepository.findByNameRecently(
                        "초격차 패키지",
                        LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L))
                );

//        System.out.println("findBookNameAndCategory : " + bookRepository.findByBookNameAndCategory());

//        bookRepository.findByBookNameAndCategory().forEach(b -> {
//            System.out.println(b.getName() + " : " + b.getCategory());
//        });

        bookRepository.findByBookNameAndCategory(PageRequest.of(1, 1)).forEach(
                bookNameAndCategoryClazz -> System.out.println(bookNameAndCategoryClazz.getName() + " : " + bookNameAndCategoryClazz.getCategory())
        );

        bookRepository.findByBookNameAndCategory(PageRequest.of(0, 1)).forEach(
                bookNameAndCategoryClazz -> System.out.println(bookNameAndCategoryClazz.getName() + " : " + bookNameAndCategoryClazz.getCategory())
        );
    }

    @Test
    public void nativeQueryTest(){
//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllCustom().forEach(System.out::println);

//        List<Book> books = bookRepository.findAll();
//        for (Book book : books) {
//            book.setCategory("IT전문서");
//        }
//        bookRepository.saveAll(books);
//        System.out.println(bookRepository.findAll());
//
//        System.out.println("updateCategories : "+bookRepository.updateCategories());
//        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println(bookRepository.showTables());
    }

    @Test
    public void convertTest(){
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또 다른 IT 전문 서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);


        System.out.println(bookRepository.findRawRecord().values());
    }

    private void givenBookAndReview() {
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("package");
        book.setAuthorId(1L);
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        return publisherRepository.save( Publisher.builder().name("패캠").build() );
    }


    private void givenReview(User user, Book book) {
        reviewRepository.save(
                Review.builder()
                        .title("내 인생을 바꾼책")
                        .content("재밌음")
                        .score(5.0f)
                        .user(user)
                        .book(book)
                        .build()
        );

    }
}