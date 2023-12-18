package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.BoardEntity;
import com.example.ws_ai_doc.entity.SubBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubBoardRepository extends JpaRepository<SubBoardEntity, Long>
{
    List<SubBoardEntity> findAll();
    List<SubBoardEntity> findAllByParentId( long id);

}