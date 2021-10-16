package com.fastcampus.ioc.rewindDI;

public class Main {

    public static void main(String[] args) {

        String url = "www.naver.com/books/";

        //Base 64 Encoding
        var base64Encoder = new Encoder(new Base64Encoder());
        System.out.println(base64Encoder.encode(url));


        //Url Encoding
        var urlEncoder = new Encoder(new UrlEncoder());
        System.out.println(urlEncoder.encode(url));

    }

}
