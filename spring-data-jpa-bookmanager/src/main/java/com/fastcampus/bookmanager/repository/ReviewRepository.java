package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select distinct  r from Review r join fetch r.comments")
    List<Review> findAllByFetchJoin();


    @EntityGraph(attributePaths = "comment")
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    @EntityGraph(attributePaths = "comment")
    List<Review> findAll();

}
