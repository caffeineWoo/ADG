package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.FinalReportEntity;
import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinalReportDTO { //회원 정보를 필드로 정의
    private Long id;
    private Long parentId;
    private String gptSummary;
    private String title;
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;

}

