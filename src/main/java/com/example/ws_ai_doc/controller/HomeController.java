package com.example.ws_ai_doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
    @RequestMapping("/login.html")
    public String login(Model model){
        return "login";
    }
}
