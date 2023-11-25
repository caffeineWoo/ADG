package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.HelloDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class helloController {
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello(@RequestParam String TYPE){
        return TYPE;
    }
    @GetMapping("/hello1")
    public String hello1(HelloDTO helloDTO){
        return helloDTO.toString();
    }
}
