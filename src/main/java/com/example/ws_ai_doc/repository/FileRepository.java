package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findAll();
    FileEntity findByFileTitle(String title);
}
