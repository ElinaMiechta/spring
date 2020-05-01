package com.example.rest.controller.dto;

import com.example.rest.model.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ItemDto {



    @NotBlank(message = "Invalid item name!")
    @Pattern(regexp = "([A-Za-z0-9]).{3,49}", message = "Invalid digits")
    private String name;

    @NotBlank(message = "Insert  short description!")
    @Pattern(regexp = "^(?=.{1,499}$).*", message = "Length is too long! Max 500 ")
    private String description;

    private int price;

    @NotBlank(message = "Insert currency like PLN/USD/EURO")

    private String currency;

    @NotBlank(message = "Insert  mark")
    private String mark;

    @NotBlank(message = "Insert  category")
    private String category;

    public String getCategory() {
        return category;
    }


    public String getDescription() {
        return description;
    }

    @NotBlank(message = "Insert  image url")
    private String imageUrl;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getMark() {
        return mark;
    }



    public String getImageUrl() {
        return imageUrl;
    }
}
