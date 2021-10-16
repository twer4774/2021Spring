package com.fastcampus.springasync.controller;

import com.fastcampus.springasync.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final AsyncService asyncService;

    //CompletableFuture : 다른 쓰레드에서 실행
    @GetMapping("/hello")
    public CompletableFuture hello(){
        /*asyncService.hello();

       log.info("method end");
        return "hello";*/

        log.info("completable future init");
        return asyncService.run();
    }
}
