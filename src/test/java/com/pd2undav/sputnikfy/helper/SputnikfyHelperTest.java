package com.pd2undav.sputnikfy.helper;

import com.pd2undav.sputnikfy.helper.SputnikfyHelper;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SputnikfyHelperTest {

    @Test
    void testConvert() throws IOException {
        MockMultipartFile testMultiPartFile = new MockMultipartFile("file",
                "file.xml",
                "application/xml",
                new FileInputStream(new File("resources/actividad_valid.xml")));
        File testFile = SputnikfyHelper.convert(testMultiPartFile);

        assertTrue(testFile.exists());
        assertNotNull(testFile.length());
        assertEquals(testMultiPartFile.getName(), FilenameUtils.getBaseName(testFile.getName()));
    }
}