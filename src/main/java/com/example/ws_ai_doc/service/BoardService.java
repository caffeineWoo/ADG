package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.DTO.BoardDTO;
import com.example.ws_ai_doc.DTO.ResponseBoardDTO;
import com.example.ws_ai_doc.DTO.SubBoardDTO;
import com.example.ws_ai_doc.entity.BoardEntity;
import com.example.ws_ai_doc.entity.SubBoardEntity;
import com.example.ws_ai_doc.repository.BoardRepository;
import com.example.ws_ai_doc.repository.SubBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final SubBoardRepository subBoardRepository;


    public List<BoardEntity> findAll() {//전체 게시글 조회
        return boardRepository.findAll();
    }
    public BoardEntity findById(long id) {//게시글 조회
        return boardRepository.findById(id);
    }

    public ResponseBoardDTO showContentsSubConents(long id){//원본과 댓글 정보 조회
        ResponseBoardDTO responseBoardDTO = new ResponseBoardDTO();
        responseBoardDTO.setContents(findById(id));
        responseBoardDTO.setSubContents(subBoardRepository.findAllByParentId(id));
        return responseBoardDTO;
    }

    public void SaveContents(BoardDTO boardDTO){//게시글 저장
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
    public void SaveSubContents(SubBoardDTO subBoardDTO){//댓글 저장
        SubBoardEntity subBoardEntity = SubBoardEntity.toSubBoardEntity(subBoardDTO);
        subBoardRepository.save(subBoardEntity);
    }


}