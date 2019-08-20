package com.restapi.smart.controllers;


import com.restapi.smart.security.token.PostAuthorizationToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. Sign"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("signUp")
    public String sign(){
        return "정상동작";
    }


    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUsername(Authentication authentication) {

        PostAuthorizationToken token = (PostAuthorizationToken)authentication;

        return token.getAccountContext().getUsername();
    }

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public String signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
                                       @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {
        return "결과는 비밀";
    }

}