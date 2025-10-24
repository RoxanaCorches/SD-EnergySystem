package com.example.auth.handlers.exceptions.model;

import lombok.Getter;

import java.util.Collection;
import java.util.Date;

@Getter
public class ExceptionHandlerResponseDTO {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String resource;
    private Collection<?> details;

    public ExceptionHandlerResponseDTO(String resource, String error, int status, String message, Collection<?> details, String path) {
        this.timestamp = new Date();
        this.resource = resource;
        this.error = error;
        this.status = status;
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResource(String error) {
        this.resource = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDetails(Collection<?> details) {
        this.details = details;
    }

    public void setError(String error) {
        this.error = error;
    }
}
