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
    @GetMapping("/home.html")
    public String homeHTML(Model model){
        return "home";
    }
    @GetMapping("/login.html")
    public String login(Model model){
        return "login";
    }
//    @RequestMapping("/login")
//    public String login1(Model model){
//        return "login";
//    }
    @GetMapping("/document")
    public String doc() {return "document";}
    @GetMapping("/adg.html")
    public String adg(Model model){
        return "adg";
    }
    @GetMapping("/adgresult.html")
    public String adgresult(Model model){
        return "adgresult";
    }
    @GetMapping("/signup.html")
    public String signup(Model model){
        return "signup";
    }
}
