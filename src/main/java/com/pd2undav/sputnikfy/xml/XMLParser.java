package com.pd2undav.sputnikfy.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.pd2undav.sputnikfy.helper.SputnikfyHelper;
import com.pd2undav.sputnikfy.model.Actividad;
import com.pd2undav.sputnikfy.model.ActivityMessage;
import com.pd2undav.sputnikfy.model.Agregado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLParser {

    private static final Logger log = LoggerFactory.getLogger(XMLParser.class);

    public List<ActivityMessage> parseXML(MultipartFile file){
        List<ActivityMessage> activityMessages = new ArrayList<>();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            Agregado agregado = xmlMapper.readValue(SputnikfyHelper.convert(file), Agregado.class);
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            for (Actividad actividad:agregado.getActividades()) {
                actividad.setUsuario(agregado.getUsuario());
                String json = mapper.writeValueAsString(actividad);
                activityMessages.add(new ActivityMessage("actividad."+actividad.getTipo(), json));
            }

        } catch (IOException e) {
            log.error("Parsing error", e);
        }

        return activityMessages;
    }
}
