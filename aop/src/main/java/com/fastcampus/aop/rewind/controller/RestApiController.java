package com.fastcampus.aop.rewind.controller;

import com.fastcampus.aop.aop.annotation.Timer;
import com.fastcampus.aop.rewind.annotation.Decode;
import com.fastcampus.aop.rewind.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name){
        System.out.println("get Method");
        System.out.println("get id " + id);
        System.out.println("get name " + name);

        return id + " " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user){
        System.out.println("post method : " + user);
        return user;
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user){
        System.out.println("put method : " + user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        Thread.sleep(1000*2);

    }
}
