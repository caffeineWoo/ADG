package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.DTO.DocumentItemResponse;
import com.example.ws_ai_doc.DTO.DocumentListResponse;
import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.MemberEntity;
import com.example.ws_ai_doc.service.DocumentService;
import com.example.ws_ai_doc.service.PdfGpt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class DocumentController {

    // 생성자 주입
    private final DocumentService documentService;

    @PostMapping("/API/document/report")
    public ResponseEntity<DocumentListResponse> handleReportForm(@RequestParam("DocumentType") String documentType) {
        List<DocumentEntity> documentEntityList = documentService.findAllDoc();
        List<DocumentItemResponse> documentItems = new ArrayList<>();

        long tempId = 0;
        for (DocumentEntity documentEntity : documentEntityList) {
            if (Objects.equals(documentEntity.getDocumentType(), documentType)) {
                DocumentItemResponse itemResponse = new DocumentItemResponse();
                itemResponse.setId(documentEntity.getId());
                itemResponse.setCategory(documentEntity.getDocumentCategory());
                itemResponse.setContents(documentEntity.getDocumentContents());
                itemResponse.setSource(documentEntity.getDocumentSourcekey());
                itemResponse.setTitle(documentEntity.getDocumentTitle());
                documentItems.add(itemResponse);
                System.out.println(documentItems);
            }
        }
        DocumentListResponse documentListResponse = new DocumentListResponse(documentItems);
        return ResponseEntity.ok(documentListResponse);
    }





}