package com.example.ws_ai_doc.controller;
import com.example.ws_ai_doc.DTO.LoginRequest;
import com.example.ws_ai_doc.DTO.NormalResponse;
import com.example.ws_ai_doc.entity.MemberEntity;
import com.example.ws_ai_doc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequiredArgsConstructor
public class LoginController {

    private MemberService memberService;

    @Autowired
    public void MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<NormalResponse> login(@ModelAttribute LoginRequest request) {
//        String memberEmail = request.getMemberEmail();
//        String memberPassword = request.getMemberPassword();
//        MemberEntity memberEntity = memberService.findMember(memberEmail, memberPassword);
//
//        NormalResponse normalResponse = new NormalResponse();
//        if (memberEntity != null) {// 회원을 찾았을 때의 처리
//            normalResponse.setResponse("ok");
//        } else {// 회원을 찾지 못했을 때의 처리
//            normalResponse.setResponse("no");
//        }
//        return ResponseEntity.ok(normalResponse);
////        return "home";
//    }

    @PostMapping("/login")
    public String loginPage(@ModelAttribute LoginRequest request, RedirectAttributes redirectAttributes) {
        String memberEmail = request.getMemberEmail();
        String memberPassword = request.getMemberPassword();
        MemberEntity memberEntity = memberService.findMember(memberEmail, memberPassword);

        NormalResponse normalResponse = new NormalResponse();
        if (memberEntity != null) {
            normalResponse.setResponse("ok");
        } else {
            normalResponse.setResponse("no");
        }

        redirectAttributes.addFlashAttribute("normalResponse", normalResponse);

        if ("ok".equals(normalResponse.getResponse())) {
            return "redirect:/plattform";
        } else {
            return "redirect:/login";
        }
    }
}