package com.example.testfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testFIleController {

    @GetMapping("/hello")
    public String hello(Model model){

        model.addAttribute("first","여러분");
        return "hello";
    }

    @GetMapping("/bye")
    public  String bye(Model model){
        model.addAttribute("first", "여러분");
        return "bye";
    }

    @GetMapping("/result")
    public String forin(Model model) {
        StringBuilder result = new StringBuilder();
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                result.append(i).append(" * ").append(j).append(" = ").append(i * j).append("<br> ");
            }
            result.append("<br>"); // 각 행 끝에 줄바꿈 추가
        }
        model.addAttribute("pattern", result.toString());
        return "result";
    }

    @GetMapping("/count")
    public String count(Model model){
        model.addAttribute("first","첫번째");
        model.addAttribute("second","두번째");
        model.addAttribute("third","세번째");
        return "count";
    }

}
