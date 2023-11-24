package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.DTO.MemberDTO;
import com.example.ws_ai_doc.entity.MemberEntity;
import com.example.ws_ai_doc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class MemberService {

    private final MemberRepository memberRepository; // 먼저 jpa, mysql dependency 추가

    public void save(MemberDTO memberDTO) {
        // repsitory의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }

    public MemberEntity findMember(String memberEmail, String memberPassword) {
        return memberRepository.findByMemberEmailAndMemberPassword(memberEmail, memberPassword);
    }
}