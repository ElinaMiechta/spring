package com.example.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {


    @GetMapping("/start")
    public String landingPage(ModelAndView model){
        model.addObject("title","Home");

        return "index";

    }
    @GetMapping("/about")
    public String aboutPage(ModelAndView model){
        model.addObject("title","About Us");
        return "about";

    }

    @GetMapping("/contact")
    public String contact(ModelAndView model){
        model.addObject("title","Contact");
        return "contact";

    }



}
