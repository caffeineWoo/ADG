package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class DocumentService {

    private final DocumentRepository documentRepository; // 먼저 jpa, mysql dependency 추가
    private final PdfGpt pdfGpt;
    private final ChatGpt chatGpt;

    public void nameToContents(DocumentDTO documentDTO) {
        String chatSummary = pdfGpt.getChatSummary(documentDTO.getDocumentName());
        documentDTO.setDocumentContents(chatSummary);
        documentDTO.setDocumentSourcekey(pdfGpt.getSourceId());
        documentDTO.setDocumentType("FULL");
        System.out.println("documentDTO = " + documentDTO);
        save(documentDTO);


        DocumentDTO documentDTO2 = new DocumentDTO();
        String gptSummary = chatGpt.getCategorySummary(chatSummary);
        documentDTO2.setDocumentName(documentDTO.getDocumentName());
        documentDTO2.setDocumentContents(gptSummary);
        documentDTO2.setDocumentSourcekey(documentDTO.getDocumentSourcekey());
        documentDTO2.setDocumentType("CATE");
        System.out.println("documentDTO = " + documentDTO2);
        save(documentDTO2);
    }

    public void save(DocumentDTO documentDTO) {
        // repsitory의 save 메서드 호출
        DocumentEntity documentEntity = DocumentEntity.toDocumentEntity(documentDTO);
        documentRepository.save(documentEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }
}