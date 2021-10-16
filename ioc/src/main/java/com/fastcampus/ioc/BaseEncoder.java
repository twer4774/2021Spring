package com.fastcampus.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class BaseEncoder implements IEncoder{

    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
