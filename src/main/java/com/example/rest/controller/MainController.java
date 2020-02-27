package com.example.rest.controller;

import com.example.rest.model.Client;
import com.example.rest.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rest")
public class MainController {

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @GetMapping
    public List<Client> users(){
        return clientRepository.findAll();
    }
}
