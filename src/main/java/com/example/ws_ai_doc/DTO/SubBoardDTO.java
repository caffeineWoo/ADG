package com.example.ws_ai_doc.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class SubBoardDTO { //회원 정보를 필드로 정의
    private Long id;
    private Long ParentId;//원래 게시물
    private String contents;//뎃
    private String memberName;//작성자
    private LocalDateTime insDate;


}