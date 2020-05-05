package com.pd2undav.sputnikfy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;


@RestController
public class XMLUploadController {

    @PostMapping("/")
    public ResponseEntity<UploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String mimeType = file.getContentType();
        UploadResponse response = validateXML(file);

        return new ResponseEntity<UploadResponse>(response, getHttpStatus(extension, mimeType, response.getValidation()));
    }

    private HttpStatus getHttpStatus(String extension, String mimeType, boolean validation){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;;

        if(extension.equals("xml") && mimeType.equals("application/xml") && validation){
                status = HttpStatus.OK;
        }

        return status;
    }

    private UploadResponse validateXML(MultipartFile file){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File("/usr/app/resources/actividad.xsd"));
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
