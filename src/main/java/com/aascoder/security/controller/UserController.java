package com.aascoder.security.controller;

import com.aascoder.security.dto.UserDTO;
import com.aascoder.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
        userDTO=userService.createUser(userDTO);
       return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username){
        UserDTO userDTO=userService.getUserByUserName(username);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Success");
    }

}
