package com.fastcampus.bookmanager.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*예제를 위해 만든 dto*/
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public interface BookNameAndCategory {

//    private String name;
//    private String category;

    String getName();
    String getCategory();
}
