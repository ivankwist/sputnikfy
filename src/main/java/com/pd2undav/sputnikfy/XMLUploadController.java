package com.pd2undav.sputnikfy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

@RestController
public class XMLUploadController {

    XMLValidator XMLValidator;
    ActivityHandler activityHandler;

    public XMLUploadController(com.pd2undav.sputnikfy.XMLValidator XMLValidator, ActivityHandler activityHandler) {
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
