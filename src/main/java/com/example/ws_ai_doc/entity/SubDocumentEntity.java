package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.SubBoardDTO;
import com.example.ws_ai_doc.DTO.SubDocumentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "sub_document_table") //database에 해당 이름의 테이블 생성
public class SubDocumentEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private Long parentId;//원래 게시물
    @Column
    private Long itemId;//아이템 번호
    @Column
    private String memberName;//작성자
    @Column
    private String contents;//내용

    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;


    public static SubDocumentEntity toSubDocumentEntity(SubDocumentDTO subDocumentDTO){
        SubDocumentEntity subDocumentEntity = new SubDocumentEntity();
        subDocumentEntity.setId(subDocumentDTO.getId());
        subDocumentEntity.setContents(subDocumentDTO.getContents());
        subDocumentEntity.setItemId(subDocumentDTO.getItemId());
        subDocumentEntity.setMemberName(subDocumentDTO.getMemberName());
        subDocumentEntity.setParentId(subDocumentDTO.getParentId());

        //생성시간은 자동화??
        return subDocumentEntity;

    }

}