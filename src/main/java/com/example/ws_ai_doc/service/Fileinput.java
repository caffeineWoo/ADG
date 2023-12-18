package com.example.ws_ai_doc.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class Fileinput {



    public String path;
    public String answer;


    public String TextToString(String path) {

        BufferedReader br = null;
        answer = "";
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            String s;

            while (( s = br.readLine()) != null) {
                answer += s;
            }

            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if( br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

        return answer;

    }

}