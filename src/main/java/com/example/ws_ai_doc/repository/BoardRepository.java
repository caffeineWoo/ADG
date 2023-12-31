package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.BoardEntity;
import com.example.ws_ai_doc.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>
{
    List<BoardEntity> findAll();
    BoardEntity findById(long id);

}