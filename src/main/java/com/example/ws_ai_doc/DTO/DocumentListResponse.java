package com.example.ws_ai_doc.DTO;

import lombok.Getter;

import java.util.List;
@Getter

public class DocumentListResponse {
    private List<DocumentItemResponse> items;

    public DocumentListResponse(List<DocumentItemResponse> items) {
        this.items = items;
    }

    public List<DocumentItemResponse> getItems() {
        return items;
    }

    public void setItems(List<DocumentItemResponse> items) {
        this.items = items;
    }
}