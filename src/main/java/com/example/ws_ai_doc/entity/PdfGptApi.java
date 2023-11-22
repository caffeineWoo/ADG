package com.example.ws_ai_doc.entity;
import java.util.List;
public class PdfGptApi {
    private boolean stream;
    private String sourceId;
    private List<PdfGptApi.Message> messages;

    // getters and setters

    public static class Message {
        private String role;
        private String content;

        // getters and setters
    }

    public PdfGptApi(boolean stream, String sourceId, List<Message> messages) {
        this.stream = stream;
        this.sourceId = sourceId;
        this.messages = messages;
    }
}


