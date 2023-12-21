package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.DTO.DocumentDTO;
import com.example.ws_ai_doc.DTO.FinalReportDTO;
import com.example.ws_ai_doc.DTO.SubDocumentDTO;
import com.example.ws_ai_doc.entity.DocumentEntity;
import com.example.ws_ai_doc.entity.FinalReportEntity;
import com.example.ws_ai_doc.entity.SubDocumentEntity;
import com.example.ws_ai_doc.repository.DocumentRepository;
import com.example.ws_ai_doc.repository.FinalReportRepository;
import com.example.ws_ai_doc.repository.SubDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service //스프링이 관리해주는 객체 == 스프링 빈
@RequiredArgsConstructor //controller와 같이. final 멤버변수 생성자 만드는 역할
public class DocumentService {

    private final DocumentRepository documentRepository; // 먼저 jpa, mysql dependency 추가
    private final SubDocumentRepository subDocumentRepository; // 먼저 jpa, mysql dependency 추가
    private final FinalReportRepository finalReportRepository; // 먼저 jpa, mysql dependency 추가

    private final FileService fileService; // 먼저 jpa, mysql dependency 추가
    private final PdfGpt pdfGpt;
    private final ChatGpt chatGpt;

    public void nameToContents(DocumentDTO documentDTO) {
        String title = documentDTO.getDocumentTitle();
        String SourceId = documentDTO.getDocumentSourcekey();
        String Category = documentDTO.getDocumentCategory();

        System.out.println("Title>>>>>>>>>>>>>>> " + title);
        System.out.println("Category>>>>>>>>>>>> " + Category);
        System.out.println("source>>>>>>>>>>>>>> " + SourceId);

        DocumentDTO documentDTO1 = new DocumentDTO();
        String chatSummary = pdfGpt.getChatSummary(Category, SourceId);
        documentDTO1.setDocumentCategory(Category);
        documentDTO1.setDocumentContents(chatSummary);
        documentDTO1.setDocumentSourcekey(SourceId);
        documentDTO1.setDocumentType("FULL");
        documentDTO1.setDocumentTitle(title);
        System.out.println("documentDTO = " + documentDTO1);
        save(documentDTO1);

        DocumentDTO documentDTO2 = new DocumentDTO();
        String gptSummary = chatGpt.getCategorySummary(chatSummary);
        documentDTO2.setDocumentCategory(Category);
        documentDTO2.setDocumentContents(gptSummary);
        documentDTO2.setDocumentSourcekey(SourceId);
        documentDTO2.setDocumentType("CATE");
        documentDTO2.setDocumentTitle(title);
        System.out.println("documentDTO = " + documentDTO2);
        save(documentDTO2);
    }

    public List<DocumentEntity> findAllDoc() {
        return documentRepository.findAll();
    }
    public DocumentEntity findById(long id) {
        return documentRepository.findById(id);
    }

    public List<SubDocumentEntity> findByPid(long Pid){ return subDocumentRepository.findByParentId( Pid );}
    public List<FinalReportEntity> finalFindByPid(long Pid){

        return finalReportRepository.findAllByParentId( Pid );
    }


    public void save(DocumentDTO documentDTO) {
        // repsitory의 save 메서드 호출
        DocumentEntity documentEntity = DocumentEntity.toDocumentEntity(documentDTO);
        documentRepository.save(documentEntity);
        //Repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)

    }
    public void subsave( SubDocumentDTO subdocumentDTO) {
        SubDocumentEntity subDocumentEntity = SubDocumentEntity.toSubDocumentEntity(subdocumentDTO);
        subDocumentRepository.save(subDocumentEntity);


    }
    public void finalsave(FinalReportDTO finalReportDTO) {
        FinalReportEntity finalReportEntity = FinalReportEntity.toFinalReportEntity(finalReportDTO);
        finalReportRepository.save(finalReportEntity);


    }
}