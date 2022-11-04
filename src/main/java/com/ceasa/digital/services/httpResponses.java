package com.ceasa.digital.services;

public class httpResponses {
    
    private String message;
    private int statusCode;

    
    public httpResponses(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


    public httpResponses() {
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public int getStatusCode() {
        return statusCode;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    
}
