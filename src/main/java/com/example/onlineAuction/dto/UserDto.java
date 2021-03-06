package com.example.onlineAuction.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String userRole;
}
