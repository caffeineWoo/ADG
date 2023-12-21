package com.example.ws_ai_doc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.example.ws_ai_doc.service.Fileinput;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ChatGpt {

    private final Fileinput fileinput;

    public String getCategorySummary(String Q) {

        String question = "Organize the following content into categories and output in XML format." + Q;
        // API 엔드포인트 및 헤더
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "sk-GvmTVz3iWwCAQVFPi";
        apiKey += "J0VT3BlbkFJkrF6qbFvgOdklE9Dzc6G";

        try {
            // API에 전송할 데이터
            List<String> messages = new ArrayList<>();

            String question1 = fileinput.TextToString("src/main/java/com/example/ws_ai_doc/service/textDb/question1.txt");
            String answer1 = fileinput.TextToString("src/main/java/com/example/ws_ai_doc/service/textDb/answer1.txt");

            // System message
            messages.add("{\"role\" : \"system\",\"content\" : \"You are a Xml file generator\"}");
            // User message 질문 1
            messages.add("{\"role\" : \"user\" , \"content\" : \"" + question1 + "\"}");
            // Assistant message 답변 1
            messages.add("{\"role\" : \"assistant\" , \"content\" : \"" + answer1 + "\"}");
            // Another user message
            messages.add("{\"role\" : \"user\" , \"content\" : \"" + question + "\"}");


//            String data = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"system\",\"content\":\"You are a Xml file generator\"},{\"role\":\"user\",\"content\":\"" + question + "\"}]}", Q);

            String data = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":" + messages + "}");
            System.out.println("Q = " + data);
            // API 호출
            String responseData = sendPostRequest(url, apiKey, data);
            String contents = extractContents(responseData);

//            // 결과 출력
//            System.out.println("Contents: " + contents);
//            // 결과를 화면에 표시
            System.out.println(responseData);
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred. Please check the console for details.";
        }
    }

    public String getGptFianlSummary(String Q) {

        String question = "Rewrite the following content." + Q;
        // API 엔드포인트 및 헤더
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "sk-GvmTVz3iWwCAQVFPi";
        apiKey += "J0VT3BlbkFJkrF6qbFvgOdklE9Dzc6G";

        try {
            // API에 전송할 데이터
            List<String> messages = new ArrayList<>();


            // System message
            messages.add("{\"role\" : \"system\",\"content\" : \"You are a helpful Document Constructor.\"}");
            // User message 질문 1
            messages.add("{\"role\" : \"user\" , \"content\" : \"" + question + "\"}");


//            String data = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":[{\"role\":\"system\",\"content\":\"You are a Xml file generator\"},{\"role\":\"user\",\"content\":\"" + question + "\"}]}", Q);

            String data = String.format("{\"model\":\"gpt-3.5-turbo\",\"messages\":" + messages + "}");
            System.out.println("Q = " + data);
            // API 호출
            String responseData = sendPostRequest(url, apiKey, data);
            String contents = extractContents(responseData);

//            // 결과 출력
//            System.out.println("Contents: " + contents);
//            // 결과를 화면에 표시
            System.out.println(responseData);
            return contents;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred. Please check the console for details.";
        }
    }

    private static String sendPostRequest(String url, String apiKey, String data) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();

        try {
            // 동기적으로 호출
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            // 결과 리턴
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred. Please check the console for details.";
        }
    }

    public static String extractContents(String apiResponse) {
        try {
            // API 응답을 JSON 객체로 변환
            JSONObject jsonResponse = new JSONObject(apiResponse);

            // choices 배열에서 첫 번째 객체의 message 속성의 content 값을 추출
            String contents = jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            return contents;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during parsing.";
        }
    }
}
