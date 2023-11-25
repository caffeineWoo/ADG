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
}

