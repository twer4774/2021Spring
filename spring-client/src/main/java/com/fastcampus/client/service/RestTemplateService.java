package com.fastcampus.client.service;

import com.fastcampus.client.dto.Req;
import com.fastcampus.client.dto.UserRequest;
import com.fastcampus.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    //http://localhost/api/server/hello
    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "walter")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();

       log.info(uri.toString());


        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);

        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        log.info(result.getStatusCode().toString());
        log.info(result.getBody().toString());

        return result.getBody();
    }

    public UserResponse post(){
        //http://lcalhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "walter")
                .toUri();

        log.info(uri.toString());

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest userRequest = UserRequest.builder()
                .name("walter")
                .age(10)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, userRequest, UserResponse.class);

        log.info("{} \n {} \n {}",
                response.getStatusCode(),
                response.getHeaders(),
                response.getBody()
        );

        return response.getBody();
    }

    public UserResponse exchange(){
        //http://lcalhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "walter")
                .toUri();

        log.info(uri.toString());

        //http body -> object -> object mapper -> json -> rest template -> http body json
        UserRequest userRequest = UserRequest.builder()
                .name("walter")
                .age(10)
                .build();

        RequestEntity<UserRequest> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffff")
                .body(userRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestRequestEntity, UserResponse.class);

        return response.getBody();




    }

    public Req<UserResponse> genericExchange(){
        //http://lcalhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/generic/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "walter")
                .toUri();

        log.info(uri.toString());

        //http body -> object -> object mapper -> json -> rest template -> http body json

        UserRequest userRequest = UserRequest.builder()
                .name("walter")
                .age(10)
                .build();

        Req<UserRequest> req = new Req<>();
                req.setHeader(
                        new Req.Header()
                );
                req.setResBody(
                        userRequest
                );


        RequestEntity<Req<UserRequest>> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>> response = restTemplate.exchange(requestRequestEntity, new ParameterizedTypeReference<>() {
        });

        return response.getBody();


    }
}
