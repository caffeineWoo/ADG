package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.SubDocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDocumentRepository extends JpaRepository<SubDocumentEntity, Long>
{
    List<SubDocumentEntity> findAll();
    List<SubDocumentEntity> findByParentId(long Pid);

}