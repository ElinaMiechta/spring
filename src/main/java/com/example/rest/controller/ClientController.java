package com.example.rest.controller;


import com.example.rest.controller.dto.ClientDto;
import com.example.rest.controller.dto.ClientLoginData;
import com.example.rest.controller.dto.OrderDto;
import com.example.rest.model.Item;
import com.example.rest.service.ClientService;
import com.example.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Controller
@SessionAttributes({"loggedClient"})
public class ClientController {
    private final ClientService clientService;

    private ItemService itemService;


    @Autowired
    public ClientController(ClientService clientService, ItemService itemService ) {
        this.clientService = clientService;
        this.itemService=itemService;

    }

    @GetMapping("/clients")
    public String getAllClients(){
        return String.valueOf(clientService.getAllClients());
    }


    @GetMapping("/register")
    public String register(Model model){


        model.addAttribute("client",new ClientDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("client") @Valid ClientDto clientDto,
    BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()){
            return "registerForm";
        }
        clientService.saveClientToDB(clientDto);
        return "login";

    }

    @GetMapping("/login")
    public String login(Model model){

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("client") @Valid ClientLoginData clientLoginData
    , BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "login";
        }


        clientService.logInClient(clientLoginData.getSurname(),clientLoginData.getPassword());

        List<Item> list = itemService.getAllItems();
        model.addAttribute("list",list);
        model.addAttribute("loggedClient",clientLoginData);




        return "index";

    }


}
