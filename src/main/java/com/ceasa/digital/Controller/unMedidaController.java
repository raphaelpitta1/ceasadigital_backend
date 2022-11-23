package com.ceasa.digital.Controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceasa.digital.Forms.unMedidaForm;
import com.ceasa.digital.Model.unMedidaModel;
import com.ceasa.digital.services.httpResponses;

import com.ceasa.digital.services.unMedidaService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/medidas")
public class unMedidaController {

    @Autowired
    unMedidaService unService;


    @GetMapping
    public ResponseEntity<List<unMedidaModel>> selectMedidas(){

        

        return ResponseEntity.status(200).body(unService.recuperaUnMedidas());
    }
    
    @PostMapping
    public ResponseEntity<Object> cadastraUnMedida(@Valid @RequestBody unMedidaForm unModel){

        try{
            
            httpResponses Response = unService.cadastrarUnMedida(unModel.getNome());
            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        }catch(Exception ex){

            return ResponseEntity.status(501).body(ex.getMessage());
        }

 
    }

  
    @DeleteMapping
    public ResponseEntity<Object> deleteUnMedida(@Validated @RequestBody unMedidaForm unModel){


        try{
            httpResponses Response = unService.deletarUnMedida(unModel.getNome());
            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }

    @PatchMapping
    public ResponseEntity<Object> atualizaUnMedida(@Validated @RequestBody unMedidaForm unModel){


        try{
            httpResponses Response = unService.deletarUnMedida(unModel.getNome());
            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }
}
