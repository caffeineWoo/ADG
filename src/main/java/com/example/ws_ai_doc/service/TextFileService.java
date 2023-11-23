package com.example.ws_ai_doc.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
@Service
public class TextFileService {

    public void appendTextToFile(String name, String content) throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentTime = dateFormat.format(new Date());
        name+= currentTime;

        File file = new File("/Users/seung-woo/Desktop/sw_lap/WS_AI_DOC/src/main/java/com/example/ws_ai_doc/service/textDb/"+name+".xml");

        // 파일이 존재하지 않으면 생성
        if (!file.exists()) {
            file.createNewFile();
        }

        // BufferedWriter 생성
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // 파일에 쓰기
            writer.write(
                    "<?xml version=\"1.0\" encoding=\"EUC-KR\" ?>\n<body>\n" + content + "\n</body>\n");
            writer.newLine();
        }
    }
}
