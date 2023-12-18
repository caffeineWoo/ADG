package com.example.ws_ai_doc.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "sub_board_table") //database에 해당 이름의 테이블 생성
public class SubBoardEntity { //table 역할
    //jpa ==> database를 객체처럼 사용 가능

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column
    private String ParentTitle;//원래 게시물
    @Column
    private String contents;//뎃글
    @Column
    private String memberName;//작성자


}