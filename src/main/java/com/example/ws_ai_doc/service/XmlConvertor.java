package com.example.ws_ai_doc.service;

import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class XmlConvertor {

    public void ConvertXmlToDoc(String name) throws IOException, ParserConfigurationException, SAXException {
// xml 파싱 빌드업
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // xml 파일을 document로 파싱하기
        Document document = builder.parse("/Users/seung-woo/Desktop/sw_lap/WS_AI_DOC/src/main/java/com/example/ws_ai_doc/service/textDb/"+name+".xml");

        // root 요소 가져오기
        Element root = document.getDocumentElement();
        // root 요소 확인 : 첫 태그 sample
        System.out.println("root.getNodeName : " + root.getNodeName());
        System.out.println(root.getFirstChild());
        // root 요소의 첫번째 노드는 #Text
        Node firstNode = root.getFirstChild();

        // 다음 노드는 customer
        Node customer = firstNode.getNextSibling();
        // customer 요소 안의 노드 리스트
        NodeList childList = customer.getChildNodes();
    }
}
