package com.aascoder.security.service;

import com.aascoder.security.dto.LoginDto;
import com.aascoder.security.dto.SignUpDto;
import com.aascoder.security.dto.UserDTO;
import com.aascoder.security.entity.User;
import com.aascoder.security.repository.UserRepository;
import com.aascoder.security.securityservice.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


   public String signUp(SignUpDto signUpDto){
       if(userRepository.existsByUsername(signUpDto.getUsername())){
           throw new RuntimeException("Username already exist");
       }
       if(userRepository.existsByEmail(signUpDto.getEmail())){
           throw new RuntimeException("Email already exist");
       }
       if(userRepository.existsByMobileNumber(signUpDto.getMobileNumber())){
           throw new RuntimeException("Mobile Number already exist");
       }

       User user=User.builder()
               .username(signUpDto.getUsername())
               .email(signUpDto.getEmail())
               .role(signUpDto.getRole())
               .password(passwordEncoder.encode(signUpDto.getPassword()))
               .mobileNumber(signUpDto.getMobileNumber())
               .build();
       user=userRepository.save(user);

       return "Success";


    }

    public String loginService(LoginDto loginDto) {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            User user=userRepository.findByUsername(loginDto.getUsername());

            String token=jwtService.generateToken(user);

            return token;


   }
}
