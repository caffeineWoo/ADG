package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "document_table") //database에 해당 이름의 테이블 생성
public class DocumentEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private String documentSourcekey;
    @Column(length = 10000)
    private String documentContents;
    @Column
    private String documentName;
    @Column
    private String documentType;

    public static DocumentEntity toDocumentEntity(DocumentDTO documentDTO){
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setId(documentDTO.getId());
        documentEntity.setDocumentContents(documentDTO.getDocumentContents());
        documentEntity.setDocumentName(documentDTO.getDocumentName());
        documentEntity.setDocumentSourcekey(documentDTO.getDocumentSourcekey());
        documentEntity.setDocumentType(documentDTO.getDocumentType());
        return documentEntity;
    }

}