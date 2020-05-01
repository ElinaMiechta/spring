package com.example.rest.controller.dto;

import com.example.rest.model.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ClientDto {

    private Order order;

    @NotBlank(message = "Insert your name")
    @Pattern(regexp = "([A-Za-z]).{2,49}", message = "Digits are not allowed, length from 3 to 49 letters")
    private String name;



    @NotBlank(message = "Insert your lastname")
    @Pattern(regexp = "([A-Za-z]).{3,49}", message = "Digits are not allowed, length from 3 to 49 letters")
    private String surname;

    @NotBlank(message = " email is required!")
    @Size(min = 5, message = "email is too short")
    @Pattern(regexp = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$")
    private String email;

    @NotBlank(message = "Insert your adress /str/nr/postal code")
    private String adress;

    @NotBlank(message = "Insert your city")
    private String city;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z]).{5,25}", message = "Your password must have one capital letter and one digit")
    private String password;


    public String getEmail() {
        return email;
    }

    public Order getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAdress() {
        return adress;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }
}
