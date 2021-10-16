package com.fastcampus.validation.controller;

import com.fastcampus.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {

    //BindingResult : Validation 에러가 발생할 때 결과를 저장하는 객체(메시지를 커스터마이징할 때 사용함)
    @PostMapping("/user")
    public ResponseEntity user(@Valid  @RequestBody User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(
                    objectError -> {
                        FieldError field = (FieldError) objectError;
                        String message = objectError.getDefaultMessage();

                        System.out.println("field : " + field.getField());
                        System.out.println(message);

                        sb.append("field : " + field.getField());
                        sb.append("message : " + message);
                    }
            );

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        System.out.println(user);
        return ResponseEntity.ok(user);

    }
}
