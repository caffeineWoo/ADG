package com.example.ws_ai_doc.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class NormalResponse { //회원 정보를 필드로 정의
    private String response;
}