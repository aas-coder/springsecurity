package com.aascoder.security.service;

import com.aascoder.security.dto.UserDTO;
import com.aascoder.security.entity.User;
import com.aascoder.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){
        User user=modelMapper.map(userDTO, User.class);
        user=userRepository.save(user);
        userDTO=modelMapper.map(user, UserDTO.class);
        userDTO.setPassword("************");
        return userDTO;
    }

    public UserDTO getUserByUserName(String username) {
        User user=userRepository.getByUsername(username);
        UserDTO userDTO=modelMapper.map(user, UserDTO.class);
        userDTO.setPassword("************");
        return userDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
