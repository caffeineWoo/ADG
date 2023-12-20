package com.example.ws_ai_doc.DTO;

import com.example.ws_ai_doc.entity.FinalReportEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FinalReportListDTO { //회원 정보를 필드로 정의
    private Long id;
    private List<FinalReportEntity> reportList;
}

