package com.ceasa.digital.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


public class httpSimulaLoginResponses {
    
    private String message;
    private int statusCode;
    private int idUsuario;
    private String token;

    


    public int getIdUsuario() {
        return idUsuario;
    }


    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
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

    
    public ResponseEntity<Object> simulaLoginresponseProcess() {
            
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idUsuario", this.idUsuario);
        map.put("token", this.token);
        map.put("mensagem", this.message);
        map.put("status", this.getStatusCode());
       
        
        return ResponseEntity.status(this.getStatusCode()).body(map);
    }
}
