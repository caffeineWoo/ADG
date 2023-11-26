package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.FileDTO;
import com.example.ws_ai_doc.DTO.FileUploadRequestDTO;
import com.example.ws_ai_doc.entity.FileEntity;
import com.example.ws_ai_doc.service.*;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@Controller
public class FileuploadController {

    private final PdfGptUpload pdfGptUpload;
    private final FileService fileService;


    @Autowired
    public FileuploadController(PdfGptUpload pdfGptUpload, FileService fileService) {
        this.pdfGptUpload = pdfGptUpload;
        this.fileService = fileService;
    }
    @PostMapping("/upload-file")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
        // 여기에서 파일을 처리하는 코드 작성
        if (!file.isEmpty()) {
            // 파일이 비어있지 않은 경우에만 처리
    //        System.out.println(fileUploadRequestDTO.getFileTitle());
            String sourceId = pdfGptUpload.uploadFile((MultipartFile) file);
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("Received S_ID: " + sourceId);


    //        String sourceId = pdfGptUpload.uploadFile(fileUploadRequestDTO.getFilePath());
            pdfGptUpload.save(file.getOriginalFilename(), sourceId);


        }return "adg";
    }


}
