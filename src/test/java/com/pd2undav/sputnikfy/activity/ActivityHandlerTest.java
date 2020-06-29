package com.pd2undav.sputnikfy.activity;

import com.pd2undav.sputnikfy.model.ActivityMessage;
import com.pd2undav.sputnikfy.xml.XMLParser;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ActivityHandlerTest {

    @Test
    public void testHandleXMLActivity() {
        List<ActivityMessage> activities = Arrays.asList(new ActivityMessage("topic1", "message1"),
                new ActivityMessage("topic2", "message2"));

        XMLParser mockParser = mock(XMLParser.class);
        doReturn(activities).when(mockParser).parseXML(any());

        RabbitTemplate mockRabbitTemplate = mock(RabbitTemplate.class);

        ActivityHandler handler = new ActivityHandler(mockRabbitTemplate, mockParser);

        MultipartFile parameterFile = mock(MultipartFile.class);
        handler.handleXMLActivity(parameterFile);

        verify(mockParser).parseXML(eq(parameterFile));

        verify(mockRabbitTemplate).convertAndSend(eq(ActivityHandler.SPUTNIKFY_EXCHANGE), eq("topic1"), eq("message1"));
        verify(mockRabbitTemplate).convertAndSend(eq(ActivityHandler.SPUTNIKFY_EXCHANGE), eq("topic2"), eq("message2"));

    }

}