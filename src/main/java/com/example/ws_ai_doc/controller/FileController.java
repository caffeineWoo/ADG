package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.FileDTO;
import com.example.ws_ai_doc.entity.FileEntity;
import com.example.ws_ai_doc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/file/report")
    public ResponseEntity<List<FileDTO>> handleReportForm() {
        List<FileEntity> fileEntityList = fileService.findAllFile();
        List<FileDTO> fileItems = new ArrayList<>();

        long tempId = 0;
        for (FileEntity fileEntity : fileEntityList) {

            FileDTO itemResponse = new FileDTO();
            itemResponse.setId(tempId++);
            itemResponse.setFileTitle(fileEntity.getFileTitle());
            itemResponse.setFileSourcekey(fileEntity.getFileSourcekey());
            fileItems.add(itemResponse);
            System.out.println(fileItems);
        }

        // ResponseEntity에 결과를 담아 반환
        return new ResponseEntity<>(fileItems, HttpStatus.OK);
    }
}
