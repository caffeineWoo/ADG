package com.example.ws_ai_doc.controller;
import com.example.ws_ai_doc.DTO.LoginRequest;
import com.example.ws_ai_doc.entity.MemberEntity;
import com.example.ws_ai_doc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequiredArgsConstructor
public class LoginController {

    private MemberService memberService;

    @Autowired
    public void MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/login")
    public String loginForm() {
        System.out.println("11");
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request) {
        String memberEmail = request.getMemberEmail();
        System.out.println("00");
        String memberPassword = request.getMemberPassword();
        MemberEntity memberEntity = memberService.findMember(memberEmail, memberPassword);
        if (memberEntity != null) {
            // 회원을 찾았을 때의 처리
            return "adg";
        } else {
            // 회원을 찾지 못했을 때의 처리
            return "login";
        }
//        return "home";
    }
}