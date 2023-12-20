package com.example.ws_ai_doc.entity;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.DTO.FinalReportDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "report_table") //database에 해당 이름의 테이블 생성
public class FinalReportEntity { //table 역할

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column
    private Long parentId;
    @Column
    private String title;

    @Column(length = 10000)
    private String gptSummary;

    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;

    public static FinalReportEntity toFinalReportEntity(FinalReportDTO finalReportDTO) {
        FinalReportEntity finalReportEntity = new FinalReportEntity();
        finalReportEntity.setParentId(finalReportDTO.getParentId());
        finalReportEntity.setGptSummary(finalReportDTO.getGptSummary());
        finalReportEntity.setTitle(finalReportDTO.getTitle());
        return finalReportEntity;
    }

//gpt Summary가 최종 저장됨

}