package com.example.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientLoginData {



    @NotBlank(message = "Password can not be empty")
    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z]).{5,25}", message = "Your password must have one capital letter and one digit")
    private String password;

    @NotBlank(message = " email is required!")
    @Size(min = 5, message = "email is too short")
    @Pattern(regexp = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$")
    private String email;


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
