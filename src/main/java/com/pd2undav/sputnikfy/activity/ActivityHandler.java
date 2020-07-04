package com.pd2undav.sputnikfy.activity;

import com.pd2undav.sputnikfy.model.ActivityMessage;
import com.pd2undav.sputnikfy.xml.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pd2undav.sputnikfy.config.RabbitConfig.SPUTNIKFY_EXCHANGE;

@Service
public class ActivityHandler {

    private static final Logger logger = LoggerFactory.getLogger(ActivityHandler.class);

    private final RabbitTemplate rabbitTemplate;
    private final com.pd2undav.sputnikfy.xml.XMLParser XMLParser;

    public ActivityHandler(RabbitTemplate rabbitTemplate, XMLParser XMLParser) {
        this.XMLParser = XMLParser;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void handleXMLActivity(MultipartFile file){
        List<ActivityMessage> activityMessages = this.XMLParser.parseXML(file);

        for (ActivityMessage activityMessage:activityMessages) {

            Message message = MessageBuilder
                    .withBody(activityMessage.getMessage().getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();

            rabbitTemplate.convertAndSend(SPUTNIKFY_EXCHANGE, activityMessage.getTopic(), message);
            logger.info("Sent message with routing-key '"+activityMessage.getTopic()+"': "+activityMessage.getMessage());
        }
    }
}
