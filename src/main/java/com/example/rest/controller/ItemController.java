package com.example.rest.controller;

import com.example.rest.controller.dto.ClientDto;
import com.example.rest.controller.dto.ClientLoginData;
import com.example.rest.model.Client;
import com.example.rest.model.Item;
import com.example.rest.repository.ClientRepository;
import com.example.rest.service.ClientService;
import com.example.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class ItemController {
    private final ItemService itemService;
    private ClientService clientService;



    @Autowired
    public ItemController(ItemService itemService, ClientService clientService) {

        this.itemService = itemService;

        this.clientService=clientService;

    }



    @GetMapping("/items")
    public String addToLoadingPageContentFromDB(Model model , ClientDto clientDto) {

        List<Item> list = itemService.getAllItems();
        model.addAttribute("list",list);


        model.addAttribute("client",clientDto);


        return "index";
    }




    @GetMapping(value = "/item/{name}")
    public String showItemPage(Model model, @PathVariable String name, @ModelAttribute("user") @Valid Client client ) {
        Item item = itemService.getByName(name);


        model.addAttribute("item", item);


        return "item";

    }

    @GetMapping(value = "/items/{category}")
    public String showItemsOfACategory(Model model, @PathVariable String category, @ModelAttribute("client") @Valid Client client){
        List<Item> list = itemService.findByCategory(category);
        Item item1 = itemService.getByCategory(category);
        model.addAttribute("list",list);
        model.addAttribute("item1",item1);



        return "categories/categories";

    }


}
