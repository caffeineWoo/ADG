package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DocumentDTO { //회원 정보를 필드로 정의
    private Long id;
    private String documentContents;
    private String documentSourcekey;
    private String documentName;
    private String documentType;

    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

    public static DocumentDTO toDocumentDTO(DocumentEntity documentEntity){
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(documentEntity.getId());
        documentDTO.setDocumentContents(documentEntity.getDocumentContents());
        documentDTO.setDocumentName(documentEntity.getDocumentName());
        documentDTO.setDocumentSourcekey(documentEntity.getDocumentSourcekey());
        documentDTO.setDocumentType(documentEntity.getDocumentType());

        return documentDTO;
    }
}