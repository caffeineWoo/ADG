package com.example.ws_ai_doc.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

//lombok dependency추가
@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestFianlReportDTO {
    private Long id;
    private Long ParentId;
    private String Summary;
    private String title;
    @CreationTimestamp
    @Column(name = "ins_date")
    private LocalDateTime insDate;
}

