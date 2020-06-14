package com.pd2undav.sputnikfy.xml;

import com.pd2undav.sputnikfy.helper.SputnikfyHelper;
import com.pd2undav.sputnikfy.model.UploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

@Service
public class XMLValidator {

    Logger logger = LoggerFactory.getLogger(XMLValidator.class);

    public UploadResponse validateXML(MultipartFile file){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("resources/actividad.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(SputnikfyHelper.convert(file)));
        } catch (IOException | org.xml.sax.SAXException e) {
            logger.error("Error de validacion", e);
            return new UploadResponse(false, e.getMessage());
        }

        return new UploadResponse(true);
    }
}
