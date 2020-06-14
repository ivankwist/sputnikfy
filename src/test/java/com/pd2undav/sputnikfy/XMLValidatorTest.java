package com.pd2undav.sputnikfy;

import com.pd2undav.sputnikfy.xml.XMLValidator;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorTest {

    @Test
    public void testValidXMLValidation() throws Exception {
        XMLValidator validator = new XMLValidator();
        MockMultipartFile testFile = new MockMultipartFile("file",
                "file.xml",
                "application/xml",
                new FileInputStream(new File("resources/actividad_valid.xml")));

        assertTrue(validator.validateXML(testFile).getValidation());
    }

    @Test
    public void testNotValidXMLValidation() throws Exception {
        XMLValidator validator = new XMLValidator();
        MockMultipartFile testFile = new MockMultipartFile("file",
                "file.xml",
                "application/xml",
                new FileInputStream(new File("resources/actividad_not_valid.xml")));

        assertFalse(validator.validateXML(testFile).getValidation());
    }
}