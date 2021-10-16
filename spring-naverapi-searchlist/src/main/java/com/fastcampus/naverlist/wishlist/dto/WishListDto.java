package com.fastcampus.naverlist.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WishListDto{

    private Integer index;
    private String title; //음식명, 장소명
    private String category;
    private String address; //주소
    private String roadAddress; //도로명
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate; //마지막 방문일자
}
