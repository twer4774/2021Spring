package com.fastcampus.interceptor.interceptor;

import com.fastcampus.interceptor.annotation.Auth;
import com.fastcampus.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}", url);

        //권한 체크
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {} " , hasAnnotation);

        // public으로 동작하지만, Auth 권한을 가진 요청에 대해서는 세션, 쿠키 등을 검사한다.
        if(hasAnnotation){
            //권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);

            if(query.equals("name=walter")){
                return true;
            }

            throw new AuthException();
        }

        return true;
    }

    //class는 예약어이므로 clazz로 대체하여 사용한다.
    public boolean checkAnnotation(Object handler, Class clazz){

        //resource js, html
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        //annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            //Auth annotation이 있을 때 true
            return true;
        }

        return false;
    }
}
