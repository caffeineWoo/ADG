package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.FinalReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinalReportRepository extends JpaRepository<FinalReportEntity, Long>
{
    List<FinalReportEntity> findAllByParentId( Long Pid);

}