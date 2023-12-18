package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.BoardDTO;
import com.example.ws_ai_doc.DTO.SubBoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "sub_board_table") //database에 해당 이름의 테이블 생성
public class SubBoardEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private Long parentId;//원래 게시물
    @Column
    private String contents;//뎃글
    @Column
    private String memberName;//작성자
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;
    public static SubBoardEntity toSubBoardEntity(SubBoardDTO subBoardDTO){
        SubBoardEntity subBoardEntity = new SubBoardEntity();
//        boardEntity.id = boardDTO.getId();
        subBoardEntity.id = subBoardDTO.getId();
        subBoardEntity.contents = subBoardDTO.getContents();
        subBoardEntity.parentId = subBoardDTO.getParentId();
        subBoardEntity.memberName = subBoardDTO.getMemberName();
        subBoardEntity.insDate = subBoardDTO.getInsDate();
        //생성시간은 자동화??
        return subBoardEntity;

    }

}