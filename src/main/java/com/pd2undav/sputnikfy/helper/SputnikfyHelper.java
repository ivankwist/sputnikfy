package com.pd2undav.sputnikfy.helper;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SputnikfyHelper {

    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File("resources/"+file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
