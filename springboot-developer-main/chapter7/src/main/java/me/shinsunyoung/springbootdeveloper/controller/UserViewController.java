package me.shinsunyoung.springbootdeveloper.controller;

import lombok.extern.slf4j.Slf4j;
import me.shinsunyoung.springbootdeveloper.domain.User;

import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Slf4j
@Controller
public class UserViewController {


    @GetMapping("/login")
    public String login() {
        log.info("[BlogViewController] login");
            return "login";
    }


    @GetMapping("/signup")
    public String signup() {
        log.info("[BlogViewController] signup");
        return "signup";
    }
}
