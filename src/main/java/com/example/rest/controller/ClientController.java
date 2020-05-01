package com.example.rest.controller;


import com.example.rest.controller.dto.ClientDto;
import com.example.rest.controller.dto.ClientLoginData;

import com.example.rest.model.Client;
import com.example.rest.model.Item;
import com.example.rest.service.ClientService;
import com.example.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
public class ClientController {
    private final ClientService clientService;

    private ItemService itemService;


    @Autowired
    public ClientController(ClientService clientService, ItemService itemService) {
        this.clientService = clientService;
        this.itemService = itemService;

    }

    @GetMapping("/clients")
    public String getAllClients() {
        return String.valueOf(clientService.getAllClients());
    }


    @GetMapping("/register")
    public String register(Model model) {


        model.addAttribute("client", new ClientDto());
        return "registerForm";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("client") @Valid ClientDto clientDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registerForm";
        }
        clientService.saveClientToDB(clientDto);

        return "login";

    }

    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("client") @Valid ClientLoginData clientLoginData
            , BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        Client client = clientService.findClientbyEmail(clientLoginData.getEmail());
        clientService.deactivateClient(client.getLoginDate());

        clientService.logInClient(clientLoginData.getEmail());
        clientService.updateLoginDate(clientLoginData.getEmail());




        List<Item> list = itemService.getAllItems();
        model.addAttribute("list", list);
        model.addAttribute("loggedUser", client);


        if (client.getRole().equals("ADMIN")){
            return "/administrator";
        }


        return "index";

    }

    @GetMapping(value = "/{token}")
    public String activateAccount(@PathVariable String token, Model model) {
        Client client = clientService.findClientbyToken(token);
        clientService.activateAccount(token);
        model.addAttribute("client", client);

        return "confirmTemplates/registerConfirm";
    }
    @GetMapping("/registerConfirm")
    public String registrationSuccess(@ModelAttribute("client") @Valid Client client){
        return "confirmTemplates/registerConfirm";
    }

    @PostMapping("/activation")
    public String sendNewActivationToken(@Valid ClientLoginData clientLoginData){
        String token = UUID.randomUUID().toString();
        clientService.resendTokenToActivateAccount(token,clientLoginData.getEmail());

        return "index";
    }
    @GetMapping("/activation")
    public String activationAccount(){
        return "confirmTemplates/activationAccount";
    }






}
