package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.SubDocumentEntity;
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
public class SubDocumentDTO { //회원 정보를 필드로 정의
    private Long id;
    private Long parentId;//원래 게시물
    private Long itemId;//아이템 번호
    private String memberName;//작성자
    private String contents;//내용
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;



}

