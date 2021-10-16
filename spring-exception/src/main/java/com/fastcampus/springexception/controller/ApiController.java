package com.fastcampus.springexception.controller;

import com.fastcampus.springexception.dto.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated
public class ApiController {

    @GetMapping("")
    public User get(@Size(min =2)
                    @RequestParam String name,

                    @NotNull
                    @Min(1)
                    @RequestParam(required = false) int age){

        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;

        return user;

    }

    @PostMapping("")
    public User post(@RequestBody User user){

        System.out.println(user);
        return user;
    }

}

