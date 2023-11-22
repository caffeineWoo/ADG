package com.example.ws_ai_doc.controller;

import com.example.ws_ai_doc.service.PdfGpt;
import com.example.ws_ai_doc.service.ChatGpt;
import com.example.ws_ai_doc.service.TextFileService;
import com.example.ws_ai_doc.service.XmlConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class apiController {

    private final PdfGpt pdfGpt;
    private final ChatGpt chatGpt;
    private final TextFileService textFileService;
    private final XmlConvertor  xmlConvertor;


    @Autowired
    public apiController(PdfGpt pdfGpt, ChatGpt chatGpt, TextFileService textFileService,XmlConvertor  xmlConvertor) {
        this.pdfGpt = pdfGpt;
        this.chatGpt = chatGpt;
        this.textFileService = textFileService;
        this.xmlConvertor = xmlConvertor;
    }

    @GetMapping("/api/pdf")
    public String pdfsummary(@RequestParam(name = "name", defaultValue = "history of os") String name) throws IOException {
        String pdfSummary = pdfGpt.getChatSummary(name);
        String chatSummary = chatGpt.getCategorySummary(pdfSummary);
//        textFileService.appendTextToFile( name+" pdf_Summary", pdfSummary );
        textFileService.appendTextToFile( name+" XML_Summary", chatSummary );

        return chatSummary;
    }
    @GetMapping("/api/doc")
    public void xmlTodoc(@RequestParam(name = "name", defaultValue = "Guest") String name) throws IOException, ParserConfigurationException, SAXException {
        xmlConvertor.ConvertXmlToDoc("history of os XML_Summary20231115_163754");
    }
}
