package com.pd2undav.sputnikfy.xml;

import com.pd2undav.sputnikfy.activity.ActivityHandler;
import com.pd2undav.sputnikfy.model.UploadResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

@RestController
public class XMLUploadController {

    private static final Logger log = LoggerFactory.getLogger(XMLUploadController.class);

    private final XMLValidator XMLValidator;
    private final ActivityHandler activityHandler;

    public XMLUploadController(com.pd2undav.sputnikfy.xml.XMLValidator XMLValidator, ActivityHandler activityHandler) {
        this.XMLValidator = XMLValidator;
        this.activityHandler = activityHandler;
    }

    @PostMapping("/")
    public ResponseEntity<UploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String mimeType = file.getContentType();
        UploadResponse response = this.XMLValidator.validateXML(file);

        if(response.getValidation()){
            activityHandler.handleXMLActivity(file);
            log.info("Valid XML file. Sent to ActivityHandler.");
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
