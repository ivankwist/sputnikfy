package com.pd2undav.sputnikfy;

public class UploadResponse {
    private final boolean validation;
    private final String error;

    public UploadResponse(boolean validation) {
        this.validation = validation;
        this.error = null;
    }

    public UploadResponse(boolean validation, String error) {
        this.validation = validation;
        this.error = error;
    }

    public boolean getValidation() {
        return validation;
    }

    public String getError() {
        return error;
    }
}
