package com.fastcampus.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Base64;

//@Component
public class Encoder {

    private IEncoder iEncoder;

//    public Encoder(@Qualifier("baseEncoder") IEncoder iEncoder){
//        this.iEncoder = iEncoder;
//    }
    public Encoder( IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setiEncoder(IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
