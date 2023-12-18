package com.example.ws_ai_doc.DTO;

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
public class BoardDTO { //회원 정보를 필드로 정의
    private Long id;
    private String conctents;
    private String title;//게시물 제목
    private String memberName;//직상자
    //lombok 어노테이션으로 getter,setter,생성자,toString 메서드 생략 가능

}