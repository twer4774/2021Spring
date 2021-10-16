package com.fastcampus.aop.rewind.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.Getter;

@Data
public class User {

    private String id;
    private String pw;
    private String email;
}
