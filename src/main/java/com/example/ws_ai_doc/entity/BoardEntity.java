package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "board_table") //database에 해당 이름의 테이블 생성
public class BoardEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private String title;
    @Column
    private String contents;
    @Column
    private String memberName;


}