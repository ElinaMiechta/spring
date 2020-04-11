package com.example.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientLoginData {

    @NotBlank(message = "Insert your lastname")
    @Pattern(regexp = "([A-Za-z]).{3,49}", message = "Digits are not allowed, length from 3 to 49 letters")
    private String surname;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z]).{5,25}", message = "Your password must have one capital letter and one digit")
    private String password;

    private String name;

    @NotBlank(message = "choose user name")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }
}
