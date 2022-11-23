package com.ceasa.digital.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


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

    
   
    public ResponseEntity<Object> responseProcess() {
            
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mensagem", this.message);
        map.put("status", this.getStatusCode());
       
        
        return ResponseEntity.status(this.getStatusCode()).body(map);
    }
}
