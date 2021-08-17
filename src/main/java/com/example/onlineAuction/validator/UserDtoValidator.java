package com.example.onlineAuction.validator;

import com.example.onlineAuction.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserDtoValidator {
    public void validate(UserDto userDto, BindingResult bindingResult){

        validateEmail(userDto, bindingResult);
        validatePassword(userDto, bindingResult);

    }

    private void validateEmail(UserDto userDto, BindingResult bindingResult) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userDto.getEmail());

        if(!matcher.matches()){
            FieldError fieldError= new FieldError("userDto","email", "Invalid email.");
            bindingResult.addError(fieldError);
        }
    }
    private void validatePassword(UserDto userDto, BindingResult bindingResult) {
        String password= userDto.getPassword();
        if(password.length()<6)
        {
            FieldError fieldError= new FieldError("userDto","password", "Password must be longer than 6 characters.");
            bindingResult.addError(fieldError);
        }
    }

}
