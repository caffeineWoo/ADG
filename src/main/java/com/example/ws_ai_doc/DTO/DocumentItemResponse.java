package com.example.ws_ai_doc.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class DocumentItemResponse {
    private long id;
    private String Category;
    private String Title;
    private String contents;
    private String source;

    // Getters and setters
}

