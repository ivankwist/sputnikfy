package com.pd2undav.sputnikfy.activity;

import com.pd2undav.sputnikfy.model.ActivityMessage;
import com.pd2undav.sputnikfy.xml.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityHandler {

    Logger logger = LoggerFactory.getLogger(ActivityHandler.class);

    private final RabbitTemplate rabbitTemplate;
    private final com.pd2undav.sputnikfy.xml.XMLParser XMLParser;

    static final String SPUTNIKFY_TOPIC = "sputnikfy-topic";

    public ActivityHandler(RabbitTemplate rabbitTemplate, XMLParser XMLParser) {
        this.XMLParser = XMLParser;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void handleXMLActivity(MultipartFile file){
        List<ActivityMessage> messages = this.XMLParser.parseXML(file);

        for (ActivityMessage m:messages) {
            rabbitTemplate.convertAndSend(SPUTNIKFY_TOPIC, m.getTopic(), m.getMessage());
            logger.info("Sent message with routing-key '"+m.getTopic()+"': "+m.getMessage());
        }
    }
}
