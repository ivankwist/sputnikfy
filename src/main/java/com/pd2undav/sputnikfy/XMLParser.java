package com.pd2undav.sputnikfy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLParser {

    Logger logger = LoggerFactory.getLogger(XMLParser.class);
    @Autowired
    RabbitTemplate rabbitTemplate;

    public List<ActivityMessage> parseXML(MultipartFile file){
        List<ActivityMessage> msg_list = new ArrayList<>();
        XMLValidator xmlv = new XMLValidator();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            Agregado agregado = xmlMapper.readValue(xmlv.convert(file), Agregado.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            for (Actividad a:agregado.getActividades()) {
                a.setUsuario(agregado.getUsuario());
                String json = mapper.writeValueAsString(a);
                msg_list.add(new ActivityMessage("activividad."+a.getTipo(), json));
                rabbitTemplate.convertAndSend("sput-topic", "actividad.escucha", json);
            }

        } catch (IOException e) {
            logger.error("Error de validacion", e);
        }

        return msg_list;
    }
}
