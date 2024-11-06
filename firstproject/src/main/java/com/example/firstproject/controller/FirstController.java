package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public String nice(){
        return "greeting";
    }

    @GetMapping("/hi")
    public String Hi(Model model){
        model.addAttribute("username", "여러분");
                return "greetings";
    }

    @GetMapping("/bye")
    public String Bye(Model model){
        model.addAttribute("username", "여러분");
        return "bye";
    }

}
