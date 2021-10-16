package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

