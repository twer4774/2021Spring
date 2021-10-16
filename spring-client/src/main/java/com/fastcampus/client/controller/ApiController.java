package com.fastcampus.client.controller;

import com.fastcampus.client.dto.Req;
import com.fastcampus.client.dto.UserResponse;
import com.fastcampus.client.service.RestTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/client")
public class ApiController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/hello")
    public UserResponse getHello(){
        return restTemplateService.hello();
    }

    @GetMapping("/post")
    public UserResponse post(){
        return restTemplateService.post();
    }

    @GetMapping("/exchange")
    public UserResponse exchange(){
        return restTemplateService.exchange();
    }

    @GetMapping("/generic-exchange")
    public Req<UserResponse> genericExchange(){
        return restTemplateService.genericExchange();
    }
}
