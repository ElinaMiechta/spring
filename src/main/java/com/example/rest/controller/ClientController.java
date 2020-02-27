package com.example.rest.controller;

import com.example.rest.controller.clientDto.ClientDto;
import com.example.rest.model.Client;
import com.example.rest.service.ClientService;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /*
    @GetMapping("/clients")
    public String getAllClients(Model model){
        List<Client> clients =clientService.getAllClients();
        model.addAttribute("clients",clients);
        System.out.println(clients);

        return "hello";
    }

     */

    @GetMapping("/clients")
    public String getAllClients(){
        return String.valueOf(clientService.getAllClients());
    }

    //entrance to registration page
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

}
