package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Book;
import com.fastcampus.bookmanager.repository.dto.BookNameAndCategory;
import com.fastcampus.bookmanager.repository.dto.BookNameAndCategoryClazz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    @Query(value = "select b from Book b " +
            "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
    List<Book> findByNameRecently(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);


    @Query(value = "select b.name as name, b.category as category from Book b")
    List<BookNameAndCategory> findByBookNameAndCategory();

    /* method 오버로딩 기능으로 페이징 메소드 추가*/
    @Query(value = "select new com.fastcampus.bookmanager.repository.dto.BookNameAndCategoryClazz(b.name, b.category) from Book b")
    Page<BookNameAndCategoryClazz> findByBookNameAndCategory(Pageable pageable);

    /* native query */
    @Query(value = "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    @Transactional
    @Modifying
    @Query(value = "update book set category = 'IT 전문서'", nativeQuery = true)
    int updateCategories();

    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
