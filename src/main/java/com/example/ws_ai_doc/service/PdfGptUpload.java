package com.example.ws_ai_doc.service;

import com.example.ws_ai_doc.entity.FileEntity;
import com.example.ws_ai_doc.repository.FileRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class PdfGptUpload {

    private final FileRepository fileRepository;
    public void save(String title , String souceId) {
        // repsitory의 save 메서드 호출
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileTitle(title);
        fileEntity.setFileSourcekey(souceId);

        fileRepository.save(fileEntity);

    }
    private static final String API_URL = "https://api.chatpdf.com/v1/sources/add-file";
    private static final String API_KEY = "sec_kbNSFYGdtSjzfz6saSrtB4aiaEPHmmAY";

//    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location
            Path tempFilePath = Files.createTempFile("temp", ".pdf");
            Files.copy(file.getInputStream(), tempFilePath, StandardCopyOption.REPLACE_EXISTING);

            // Prepare the file for upload
            File tempFile = tempFilePath.toFile();
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new FileSystemResource(tempFile));

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("x-api-key", API_KEY);

            // Create the request entity
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            // Make the API request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // Delete the temporary file
            tempFile.delete();
            System.out.println(responseEntity.getBody());
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String sourceId = jsonNode.get("sourceId").asText();

            // sourceId를 변수에 저장
            System.out.println("Received sourceId: " + sourceId);
            return sourceId;
//            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file.";
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file.");
        }

    }
}
