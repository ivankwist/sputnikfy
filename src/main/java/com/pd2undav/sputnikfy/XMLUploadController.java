package com.pd2undav.sputnikfy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

import java.util.List;

@RestController
public class XMLUploadController {

    XMLValidator XMLValidator;
    XMLParser XMLParser;

    public XMLUploadController(XMLValidator XMLValidator, XMLParser XMLParser) {
        this.XMLValidator = XMLValidator;
        this.XMLParser = XMLParser;
    }

    @PostMapping("/")
    public ResponseEntity<UploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String mimeType = file.getContentType();
        UploadResponse response = this.XMLValidator.validateXML(file);
        List<ActivityMessage> mensajes = this.XMLParser.parseXML(file);

        for (ActivityMessage m:mensajes) {
            System.out.println(m.getTopic()+" - "+m.getMessage());
        }

        return new ResponseEntity<UploadResponse>(response, getHttpStatus(extension, mimeType, response.getValidation()));
    }

    private HttpStatus getHttpStatus(String extension, String mimeType, boolean validation){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;;

        if(extension.equals("xml") && mimeType.equals("application/xml") && validation){
            status = HttpStatus.OK;
        }

        return status;
    }

}
