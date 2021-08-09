package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.model.User;
import com.example.onlineAuction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(UserDto userDto)
    {
        User user= new User();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
}
