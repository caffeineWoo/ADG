package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.BoardDTO;
import com.example.ws_ai_doc.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "board_table") //database에 해당 이름의 테이블 생성
public class BoardEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private String title;
    @Column(length = 10000)
    private String contents;
    @Column
    private String memberName;
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    public static BoardEntity toBoardEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
//        boardEntity.id = boardDTO.getId();
        boardEntity.title = boardDTO.getTitle();
        boardEntity.contents = boardDTO.getContents();
        boardEntity.memberName = boardDTO.getMemberName();
        //생성시간은 자동화??
        return boardEntity;

    }

}