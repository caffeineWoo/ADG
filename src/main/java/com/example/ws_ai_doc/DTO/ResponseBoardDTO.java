package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.BoardEntity;
import com.example.ws_ai_doc.entity.SubBoardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseBoardDTO {
    private Long id;
    private BoardEntity contents;//원본
    private List<SubBoardEntity> subContents; //댓글 목록

}