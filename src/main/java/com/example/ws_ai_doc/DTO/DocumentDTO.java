package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DocumentDTO { //회원 정보를 필드로 정의
    private Long id;
    private String documentContents;
    private String documentSourcekey;
    private String documentCategory;
    private String documentTitle;
    private String documentType;
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;
    public static DocumentDTO toDocumentDTO(DocumentEntity documentEntity){
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setId(documentEntity.getId());
        documentDTO.setDocumentSourcekey(documentEntity.getDocumentSourcekey());
        documentDTO.setDocumentTitle(documentEntity.getDocumentTitle());
        documentDTO.setDocumentCategory(documentEntity.getDocumentCategory());

        return documentDTO;
    }


}

