package com.example.ws_ai_doc.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(Model model){
        return "home.html";
    }
    @RequestMapping("/login")
    public String login(Model model){
        return "login.html";
    }
}
