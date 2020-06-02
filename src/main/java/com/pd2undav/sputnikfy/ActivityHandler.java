package com.pd2undav.sputnikfy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityHandler {

    @Autowired
    RabbitTemplate rabbitTemplate;
    Logger logger = LoggerFactory.getLogger(ActivityHandler.class);
    XMLParser XMLParser;

    public ActivityHandler(com.pd2undav.sputnikfy.XMLParser XMLParser) {
        this.XMLParser = XMLParser;
    }

    public void handleXMLActivity(MultipartFile file){
        List<ActivityMessage> messages = this.XMLParser.parseXML(file);

        for (ActivityMessage m:messages) {
            rabbitTemplate.convertAndSend("sput-topic", m.getTopic(), m.getMessage());
            logger.debug("Sent message with routing-key: "+m.getTopic());
        }
    }
}
