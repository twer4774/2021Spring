package com.fastcampus.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IoCApplication {

    public static void main(String[] args) {

        /* DI 예 */
        /*
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        //Base64 Encoding
        Encoder encoder = new Encoder(new BaseEncoder()); //DI
        String result = encoder.encode(url);
        System.out.println(result);

        //urlEncoding
        Encoder urlEncoder = new Encoder(new UrlEncoder()); //DI
        String result2 = encoder.encode(url);
        System.out.println(result2);
        */


        SpringApplication.run(IoCApplication.class, args);

        //IoC
        ApplicationContext context = ApplicationContextProvider.getContext();
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        /* 기본 */
//        BaseEncoder baseEncoder = context.getBean(BaseEncoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
//        Encoder encoder = new Encoder(baseEncoder);
//
//        String result = encoder.encode(url);
//        System.out.println(result);

//        encoder.setiEncoder(urlEncoder);
//        result = encoder.encode(url);
//        System.out.println(result);

        /* Encoder에 @Component와 @Qualifier를 넣었을 때 */
//        Encoder encoder = context.getBean(Encoder.class);
//
//        String result = encoder.encode(url);
//        System.out.println(result);

        /* @Configuration으로 여러개의 Bean을 직접 생성했을 때 */
        Encoder encoder = context.getBean("base64Encode", Encoder.class);
        String result = encoder.encode(url);
        System.out.println(result);
    }
}


//한개의 클래스에 여러개의 bean을 등록하는 경우
/*
Encoder의 @Qualifier와 @Component를 제거 후 직접 Bean으로 등록
*/

@Configuration
class AppConfig {

    @Bean("base64Encode")
    public Encoder encoder(BaseEncoder baseEncoder) {
        return new Encoder(baseEncoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}