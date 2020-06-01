package com.pd2undav.sputnikfy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ActivityHandler {

    @Autowired
    RabbitTemplate rabbitTemplate;
    XMLParser XMLParser;

    public ActivityHandler(com.pd2undav.sputnikfy.XMLParser XMLParser) {
        this.XMLParser = XMLParser;
    }

    public void handleXMLActivity(MultipartFile file){
        List<ActivityMessage> messages = this.XMLParser.parseXML(file);

        for (ActivityMessage m:messages) {
            rabbitTemplate.convertAndSend("sput-topic", m.getTopic(), m.getMessage());
        }
    }
}
