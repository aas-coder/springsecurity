package com.aascoder.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(unique = true,nullable = false)
    String username;
    @Column(unique = true,nullable = false)
    String mobileNumber;
    @Column(unique = true,nullable = false)
    String email;
    @Column(nullable = false)
    String password;

    @Column
    String role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>(Collections.singleton(new SimpleGrantedAuthority(role)));
    }
}
