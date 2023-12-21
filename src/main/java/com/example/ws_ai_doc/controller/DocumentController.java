package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.*;
import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.FinalReportEntity;
import com.example.ws_ai_doc.entity.MemberEntity;
import com.example.ws_ai_doc.service.ChatGpt;
import com.example.ws_ai_doc.service.DocumentService;
import com.example.ws_ai_doc.service.PdfGpt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.CheckedOutputStream;

@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class DocumentController {

    // 생성자 주입
    private final DocumentService documentService;
    private final ChatGpt chatGpt;

    @PostMapping("/API/document/save")    // name값을 requestparam에 담아온다
    public String save(@ModelAttribute DocumentDTO documentDTO) {
        System.out.println("DocumentController.save");
        System.out.println("documentDTO = " + documentDTO);
        documentService.nameToContents(documentDTO);
        NormalResponse normalResponse = new NormalResponse();
        normalResponse.setResponse("ok");
        return "redirect:documentDetail";
    }
    @PostMapping("/API/subdocument/save")    // name값을 requestparam에 담아온다
    public String subsave(@ModelAttribute SubDocumentDTO subdocumentDTO) {
        documentService.subsave(subdocumentDTO);
        NormalResponse normalResponse = new NormalResponse();
        normalResponse.setResponse("ok");
        return "redirect:documentDetail";
    }
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
    @PostMapping("API/documentDetail")
    public ResponseEntity<ResponseDocumentDTO> getDocumentDetail(@RequestParam("dockey") long id) {
        ResponseDocumentDTO responseDocumentDTO = new ResponseDocumentDTO();
        responseDocumentDTO.setDocument(documentService.findById(id));
        responseDocumentDTO.setSubDocumentList(documentService.findByPid(id));
        System.out.println(responseDocumentDTO);
        return ResponseEntity.ok(responseDocumentDTO);
    }

    @PostMapping("API/document/combine")
    public String requesrFianlReport(@ModelAttribute RequestFianlReportDTO requestFianlReportDTO)
    {
        System.out.println(requestFianlReportDTO);
        String Summary = requestFianlReportDTO.getSummary();

        long Pid = requestFianlReportDTO.getParentId();
        String title = requestFianlReportDTO.getTitle();
        String gptSummary = chatGpt.getGptFianlSummary(Summary);
        FinalReportDTO finalReportDTO = new FinalReportDTO();

        finalReportDTO.setGptSummary(gptSummary);
        finalReportDTO.setTitle(title);
        finalReportDTO.setParentId(Pid);
        documentService.finalsave(finalReportDTO);
        //최종 답변을 저장하는 api
        return "report";
    }
    @GetMapping("API/document/combine")
    public ResponseEntity<List<FinalReportDTO>> getFianlReport(@RequestParam("Pid") long Pid)
    {//최종 답변을 요구
        System.out.println(Pid);
        List<FinalReportEntity> finalReportList = documentService.finalFindByPid(Pid);
        List<FinalReportDTO> documentItems = new ArrayList<>();

        long tempId = 0;
        for (FinalReportEntity finalReportEntity : finalReportList) {

            FinalReportDTO finalReportDTO = new FinalReportDTO();
            finalReportDTO.setId(finalReportEntity.getId());
            finalReportDTO.setParentId(finalReportEntity.getParentId());
            finalReportDTO.setGptSummary(finalReportEntity.getGptSummary());
            finalReportDTO.setTitle(finalReportEntity.getTitle());
            documentItems.add(finalReportDTO);
        }
        System.out.println(documentItems);
        List<FinalReportDTO> documentListResponse = new ArrayList<>(documentItems);
        System.out.println(documentListResponse);
        return ResponseEntity.ok(documentItems);
    }




}