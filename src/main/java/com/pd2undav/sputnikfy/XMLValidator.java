package com.pd2undav.sputnikfy;

import org.springframework.web.multipart.MultipartFile;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLValidator {

    public static UploadResponse validateXML(MultipartFile file){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("resources/actividad.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(convert(file)));
        } catch (IOException | org.xml.sax.SAXException e) {
            return new UploadResponse(false, e.getMessage());
        }

        return new UploadResponse(true);
    }

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
