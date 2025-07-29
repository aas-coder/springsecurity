package com.aascoder.security.controller;

import com.aascoder.security.dto.LoginDto;
import com.aascoder.security.dto.SignUpDto;
import com.aascoder.security.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    SignUpService signUpService;

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestBody LoginDto loginDto){
        String token=signUpService.loginService(loginDto);
       return ResponseEntity.ok().header("authToken", token).build();
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpDto signUpDto){
        String s=signUpService.signUp(signUpDto);
        return ResponseEntity.accepted().build();
    }

}
