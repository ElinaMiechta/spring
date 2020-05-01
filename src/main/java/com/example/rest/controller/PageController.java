package com.example.rest.controller;


import com.example.rest.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller("/")
public class PageController {



    @GetMapping("/")
    public String landingPage( @Valid Client client, Model model){
        model.addAttribute("user",client);

        return "index";

    }




    @GetMapping("/about")
    public String aboutPage(ModelAndView model){
        model.addObject("title","About Us");
        return "about";

    }

    @GetMapping("/contact")
    public String contact(ModelAndView modelAndView, Model model){
        modelAndView.addObject("title","Contact");


        return "contact";

    }



}
