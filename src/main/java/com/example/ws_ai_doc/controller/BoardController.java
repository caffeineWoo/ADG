package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.DTO.BoardDTO;
import com.example.ws_ai_doc.DTO.ResponseBoardDTO;
import com.example.ws_ai_doc.DTO.SubBoardDTO;
import com.example.ws_ai_doc.entity.BoardEntity;
import com.example.ws_ai_doc.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor //MemberService에 대한 멤버를 사용 가능
public class BoardController {

    // 생성자 주입
    private final BoardService boardService;

    @PostMapping("/API/board/report")    // 게시글 전체 조회. 파라미터 없음
    public ResponseEntity<List<BoardEntity>> BoardReport() {
        List<BoardEntity> boardEntityLsit = boardService.findAll();
        if (boardEntityLsit != null) {
            return ResponseEntity.ok(boardEntityLsit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/API/subboard/report") //댓글까지 조회. 파라미터 부모 id
    public ResponseEntity<ResponseBoardDTO> subBoardReport(@RequestParam("parentId") long Pid) {
        ResponseBoardDTO responseBoardDTO = boardService.showContentsSubConents(Pid);
        return ResponseEntity.ok(responseBoardDTO);
    }

    @PostMapping("/API/board/save") //게시글 저장.
//    title;//게시물 제목
//    conctents;//본문
//    memberName;//직상자
    public String boardSave(@ModelAttribute BoardDTO boardDTO) {
        boardService.SaveContents(boardDTO);
        return "board";
    }
    @PostMapping("/API/subboard/save") //게시글 저장.
//    ParentId;//원래 게시물
//    contents;//댓글
//    memberName;//작성자
    public String subboardSave(@ModelAttribute SubBoardDTO subBoardDTO) {
        boardService.SaveSubContents(subBoardDTO);
        return "boardDetail";
    }




}