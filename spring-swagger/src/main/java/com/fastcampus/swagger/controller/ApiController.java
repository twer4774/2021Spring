package com.fastcampus.swagger.controller;

import com.fastcampus.swagger.dto.UserRequest;
import com.fastcampus.swagger.dto.UserResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"API 정보를 제공하는 api"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @ApiImplicitParams({
                    @ApiImplicitParam(name = "x", value = "x 값", required = true, paramType = "path", dataType = "int"),
                    @ApiImplicitParam(name = "y", value = "y 값", required = true, paramType = "query", dataType = "int")
            })
    @GetMapping("/plus/{x}")
    public int plus(
            @PathVariable int x,
            @RequestParam int y){
        return x + y;
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 리턴하는 메소드")
    @GetMapping("/user")
    public UserResponse user(UserRequest userRequest) {
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }

    @PostMapping("/user")
    public UserResponse postUser(@RequestBody UserRequest userRequest){
        return new UserResponse(userRequest.getName(), userRequest.getAge());
    }
}
