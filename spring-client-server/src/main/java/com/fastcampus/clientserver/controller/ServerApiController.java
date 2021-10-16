package com.fastcampus.clientserver.controller;

import com.fastcampus.clientserver.dto.Req;
import com.fastcampus.clientserver.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.digester.DocumentProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public User hello(@RequestParam String name, @RequestParam int age){
        User user = User.builder()
                .name(name)
                .age(age)
                .build();
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(
        @RequestBody User user,
        @PathVariable("userId") int userId,
        @PathVariable("userName") String userName,
        @RequestHeader("x-authorization") String authorization,
        @RequestHeader("custom-header") String customHeader

    ) {
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, header : {}", authorization, customHeader);
        log.info("client req : {}", user);

        return user;
    }

    @PostMapping("/generic/user/{userId}/name/{userName}")
    public Req<User> reqPost(
            @RequestBody Req<User> user,
            @PathVariable("userId") int userId,
            @PathVariable("userName") String userName,
            @RequestHeader("x-authorization") String authorization,
            @RequestHeader("custom-header") String customHeader

    ) {
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, header : {}", authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setResBody(user.getResBody());
        return user;
    }

    //https://openapi.naver.com/v1/search/local.json
    // ?query=%EC%A3%BC%EC%8B%9D
    // &display=10
    // &start=1
    // &sort=random
    @GetMapping("/naver")
    public String naver(){

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", "갈비집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "DGra3RlayAAkG_cCtHQP")
                .header("X-Naver-Client-Secret", "tSsg241STq")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();

    }
}
