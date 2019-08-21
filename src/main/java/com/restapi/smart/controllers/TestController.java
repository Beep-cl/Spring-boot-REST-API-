package com.restapi.smart.controllers;



import com.restapi.smart.security.token.PostAuthorizationToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("signUp")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String sign(){
        return "정상동작";
    }


    @RequestMapping("chkhello")
    @PreAuthorize("isAuthenticated()")
    public String chkhello(){
        return "정상동작 isAuth~ 로그인되어있으면";
    }

    @RequestMapping("chkhello2")
    @PreAuthorize("isAnonymous()")
    public String chkhello2(){
        return "정상동작 isAnonymous ~ 로그인 안되어있으면";
    }

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_DEFAULT')")
    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken)authentication;

        return token.getAccountContext().getUsername();
    }

}