package com.example.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AdminDto {

    private String name;
    @NotBlank(message = "Password can not be empty")
    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z]).{5,25}", message = "Your password must have one capital letter and one digit")
    private String password;

    private String role;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }


    public String getPassword() {
        return password;
    }


}
