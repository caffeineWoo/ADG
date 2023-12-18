package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class DocSaveController {

    private final DocumentService documentService;

    @PostMapping("/API/document/save")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute DocumentDTO documentDTO) {
        System.out.println("DocumentController.save");
        System.out.println("documentDTO = " + documentDTO);
        documentService.nameToContents(documentDTO);
        return "adg";
    }
}
