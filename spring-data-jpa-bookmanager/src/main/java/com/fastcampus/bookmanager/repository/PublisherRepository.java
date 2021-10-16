package com.fastcampus.bookmanager.repository;

import com.fastcampus.bookmanager.domain.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
