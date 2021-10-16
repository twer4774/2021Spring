package com.fastcampus.bookmanager.domain.entity;

import com.fastcampus.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable{

    @CreatedDate
    //columnDefinition은 auto-ddl의 생성 속성이다. now(6)의 숫자 6은 시분초 다음 나노초 단위 자릿수를 의미한다(기본은 시분초까지만 나옴).
    @Column(columnDefinition = "datetime(6) default now(6)",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6)", nullable = false)
    private LocalDateTime updatedAt;
}
