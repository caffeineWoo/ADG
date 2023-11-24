package com.example.ws_ai_doc.repository;

import com.example.ws_ai_doc.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>
{
    MemberEntity findByMemberEmailAndMemberPassword(String memberEmail, String memberPassword);

}