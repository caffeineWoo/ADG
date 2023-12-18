package com.example.ws_ai_doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
//    @GetMapping("/home")
//    public String homeHTML(Model model){
//        return "home";
//    }
    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
//    @RequestMapping("/login")
//    public String login1(Model model){
//        return "login";
//    }
    @GetMapping("/ADG/document")
    public String document() {return "document";}
    @GetMapping("/ADG/documentDetail")
    public String documentDtail() {return "documentDetail";}
    @GetMapping("/ADG/board")
    public String board() {return "board";}
    @GetMapping("/ADG/boardDetail")
    public String boardDtail() {return "boardDetail";}
    @GetMapping("/ADG/board/new")
    public String boardNew() {return "boardNew";}
    @GetMapping("/")
    public String home() {return "home";}
    @GetMapping("/plattform")
    public String plattform() {return "plattform";}




//    @GetMapping("/ADG")
//    public String adg(Model model){
//        return "document";
//    }

    @GetMapping("/signup")
    public String signup(Model model){
        return "signup";
    }
}
