package com.example.ws_ai_doc.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//lombok dependency추가
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FileUploadRequestDTO {
    private String filePath;
    private String fileTitle;
}
