package com.pd2undav.sputnikfy;

import com.pd2undav.sputnikfy.model.UploadResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UploadResponseTest {

    @Test
    public void testConstructorValidResponse() {
        boolean validation = true;
        UploadResponse uploadResponse = new UploadResponse(validation);
        assertNull(uploadResponse.getError());
        assertEquals(uploadResponse.getValidation(), validation);
    }

    @Test
    public void testConstructorNotValidResponse() {
        boolean validation = true;
        String error = "Error";
        UploadResponse uploadResponse = new UploadResponse(validation, error);
        assertEquals(uploadResponse.getError(), error);
        assertEquals(uploadResponse.getValidation(), validation);
    }
}