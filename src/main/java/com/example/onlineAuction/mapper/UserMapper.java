package com.example.onlineAuction.mapper;

import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.model.User;
import com.example.onlineAuction.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        String encodedPassword= bCryptPasswordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.valueOf(userDto.getUserRole()));
        return user;
    }
}
