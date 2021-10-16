package com.fastcampus.aop.controller;

import com.fastcampus.aop.aop.annotation.Decode;
import com.fastcampus.aop.aop.annotation.Timer;
import com.fastcampus.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        return user;
    }

    /*
    aop-put에서 동작확인
    결과로 body의 base64형태로 반환되지만, 서버 로그에서는 test@gmail처럼 디코딩된 결과를 볼 수 있다.
     */
    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put");
        System.out.println(user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //db logic
        Thread.sleep(2000);
    }
}
