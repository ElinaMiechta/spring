package com.example.rest.controller;

import com.example.rest.controller.dto.ItemDto;
import com.example.rest.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/upload")
    public String uploadItem(Model model, ItemDto itemDto){
        model.addAttribute("item", itemDto);

        return "upload";

    }

    @PostMapping("/upload")
    public String addItem(@ModelAttribute("item") @Valid ItemDto item, BindingResult bindingResult) throws Exception {


        if (bindingResult.hasErrors()) {

            return "upload";
        }


        itemService.saveItemToDB(item);

        return "index";
    }


}
