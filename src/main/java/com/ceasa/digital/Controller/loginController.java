package com.ceasa.digital.Controller;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceasa.digital.Forms.atualizaSenhaUsuarioForm;
import com.ceasa.digital.Forms.atualizaStatusUsuarioForm;
import com.ceasa.digital.Forms.atualizaUsuarioForm;
import com.ceasa.digital.Forms.cadastraUsuarioForm;
import com.ceasa.digital.Forms.loginForm;
import com.ceasa.digital.Forms.loginUsuarioForm;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.httpSimulaLoginResponses;
import com.ceasa.digital.services.userService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class loginController {

    @Autowired
    userService uService;


   
    @PostMapping
    public ResponseEntity<String> login(@Validated @RequestBody loginForm lForm) throws UnirestException{

        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.post("https://ceasadigitalback.herokuapp.com/oauth/token")
          .header("Authorization", "Basic OTk1MjdiMTgtMjljMi00NTk4LTk0YTYtZDQ2MGU2MDZhYmYwOjhkMWUyMjk4LTRkYTgtNDFkNy05MmJhLThiOTlhMzE1MmUxOQ==")
          .header("Content-Type", "application/x-www-form-urlencoded")
          .field("username",lForm.getUser())
          .field("password",lForm.getPassword())
          .field("grant_type", "password")
          .asString();

         
          return ResponseEntity.status(response.getStatus()).body(response.getBody());

    }

    
}
