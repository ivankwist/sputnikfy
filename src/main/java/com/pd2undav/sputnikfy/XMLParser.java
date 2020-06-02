package com.pd2undav.sputnikfy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLParser {

    Logger logger = LoggerFactory.getLogger(XMLParser.class);
    XMLValidator XMLValidator;

    public List<ActivityMessage> parseXML(MultipartFile file){
        List<ActivityMessage> msg_list = new ArrayList<>();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            Agregado agregado = xmlMapper.readValue(this.XMLValidator.convert(file), Agregado.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            for (Actividad a:agregado.getActividades()) {
                a.setUsuario(agregado.getUsuario());
                String json = mapper.writeValueAsString(a);
                msg_list.add(new ActivityMessage("activividad."+a.getTipo(), json));
            }

        } catch (IOException e) {
            logger.error("Error de parseo", e);
        }

        return msg_list;
    }
}
