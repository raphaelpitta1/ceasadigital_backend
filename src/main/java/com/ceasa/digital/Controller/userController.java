package com.ceasa.digital.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.userService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController

public class userController {

    @Autowired
    userService uService;


    @GetMapping("api/usuarios")
    public ResponseEntity<List<userModel>> selectUsuarios(){

        

        return ResponseEntity.status(200).body(uService.recuperaUsuarios());
    }
    
    @PostMapping("/api/cadastrousuario")
    public ResponseEntity<String> cadastraUsuario(@Validated @RequestBody userModel umModel){

        try{
            
          httpResponses Response = uService.cadastrarUsuario(umModel.getNome(), umModel.getSobrenome(), umModel.getTipo_pessoa().toString(), umModel.getDocumento(),umModel.getSenha(), umModel.getTelefone());
            return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        }catch(Exception ex){

            return ResponseEntity.status(501).body(ex.getMessage());
        }

 
    }

    @PatchMapping("/api/atualizausuario")
    public ResponseEntity<String> atualizaUsuario(@Validated @RequestBody userModel umModel) {

try{
    httpResponses Response = uService.atualizaUsuario(umModel.getNome(), umModel.getSobrenome(), umModel.getDocumento(),umModel.getTelefone());
    
    return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

}catch(Exception ex){

    return ResponseEntity.status(501).body(ex.getMessage());

}
      
    }


    @PatchMapping("/api/atualizastatus")
    public ResponseEntity<String>mudarstatus(@Validated @RequestBody userModel umModel){


        try{
            httpResponses Response = uService.mudarStatusUsuario(umModel.getDocumento(), umModel.getStatus());
            return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }
}
