package com.aascoder.security.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SignUpDto {
    String username;
    String mobileNumber;
    String email;
    String password;
    String role;
}
