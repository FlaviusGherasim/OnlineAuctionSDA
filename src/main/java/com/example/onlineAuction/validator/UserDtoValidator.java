package com.example.onlineAuction.validator;

import com.example.onlineAuction.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
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
        validateFirstNameAndLastName(userDto, bindingResult);
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
//    private void validatePassword(UserDto userDto, BindingResult bindingResult) {
//        String password= userDto.getPassword();
//        if(password.length()<6)
//        {
//            FieldError fieldError= new FieldError("userDto","password", "Password must be longer than 6 characters.");
//            bindingResult.addError(fieldError);
//        }
//    }
    // "^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"

    private void validatePassword(UserDto userDto, BindingResult bindingResult)
    {
        String regexForPassword= "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
        Pattern pattern= Pattern.compile(regexForPassword);
        Matcher matcher= pattern.matcher(userDto.getPassword());

        if(!matcher.matches())
        {
            FieldError fieldError= new FieldError("userDto","password", "Minimum eight characters, at least one letter, one number and one special character:");
            bindingResult.addError(fieldError);
        }
    }

    private void validateFirstNameAndLastName(UserDto userDto, BindingResult bindingResult) {
        String regex = "[a-zA-Z]+";

        String firstName= userDto.getFirstName();
        String lastName= userDto.getLastName();

        if(firstName.isEmpty()|| !firstName.matches(regex)|| StringUtils.isBlank(firstName)){
            FieldError fieldError= new FieldError("userDto","firstName", "The name must contain only letters.");
            bindingResult.addError(fieldError);
        }

        if(lastName.isEmpty()|| !lastName.matches(regex) || StringUtils.isBlank(lastName)){
            FieldError fieldError= new FieldError("userDto","lastName", "The name must contain only letters.");
            bindingResult.addError(fieldError);
        }
    }

}
