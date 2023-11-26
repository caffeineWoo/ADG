package com.example.ws_ai_doc.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PdfGpt {

    private static String PDF_String = "";

    String url = "https://api.chatpdf.com/v1/chats/message";
    String apiKey = "sec_kbNSFYGdtSjzfz6saSrtB4aiaEPHmmAY";
//    String sourceId = "src_nHxPSWHckJ9ONgMjTCNZD";
//여기를 이제 사용자가 소스 아이디를 보내주는걸 사용해야함.
//    public String getSourceId() {
//        return sourceId;
//    }

    public String getChatSummary(String Q, String sourceId) {
        // 질문
        String question = "show all contents" + Q;

        // API 엔드포인트 및 헤더

        try {
            // API에 전송할 데이터

            String data = "{\"stream\":true,\"sourceId\":\""+ sourceId +"\",\"messages\":[{\"role\":\"user\",\"content\":\"" + question + "\"}]}";

            // API 호출
            String responseData = sendPostRequest(url, apiKey, data);

            // 결과를 화면에 표시
            PDF_String = responseData;
            System.out.println(responseData);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return PDF_String;
    }

    private static String sendPostRequest(String url, String apiKey, String data) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // 요청 메소드 설정
        connection.setRequestMethod("POST");
        // 헤더 설정
        connection.setRequestProperty("x-api-key", apiKey);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // 데이터 전송
        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(data);
            outputStream.flush();
        }

        // 응답 읽기
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            connection.disconnect();
        }
    }
}
