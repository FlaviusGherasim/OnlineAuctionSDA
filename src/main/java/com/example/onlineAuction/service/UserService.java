package com.example.onlineAuction.service;

import com.example.onlineAuction.dto.UserDto;
import com.example.onlineAuction.mapper.UserMapper;
import com.example.onlineAuction.model.User;
import com.example.onlineAuction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public void addUser(UserDto userDto)
    {
        User user= userMapper.map(userDto);
        userRepository.save(user);
    }
}
