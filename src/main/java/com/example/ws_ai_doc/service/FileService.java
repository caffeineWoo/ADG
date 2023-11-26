package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.entity.FileEntity;
import com.example.ws_ai_doc.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
    public List<FileEntity> findAllFile() {
        return fileRepository.findAll();
    }
    public FileEntity findSource(String title){return fileRepository.findByFileTitle(title);}
}
