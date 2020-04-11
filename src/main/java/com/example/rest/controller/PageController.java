package com.example.rest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class PageController {



    @GetMapping("/start")
    public String landingPage( ){

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
