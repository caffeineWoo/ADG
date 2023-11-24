package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.service.DocumentService;
import com.example.ws_ai_doc.service.PdfGpt;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class DocumentController {
    private final PdfGpt pdfGpt;

    // 생성자 주입
    private final DocumentService documentService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/document/save")
    public String saveForm() {
        return "document";
    }

    @PostMapping("/document/save")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute DocumentDTO documentDTO) {
        System.out.println("DocumentController.save");
        System.out.println("documentDTO = " + documentDTO);
        documentService.nameToContents(documentDTO);
        return "home";
    }
}