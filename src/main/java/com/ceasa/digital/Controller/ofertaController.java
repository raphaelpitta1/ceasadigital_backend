package com.ceasa.digital.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.ceasa.digital.Forms.atualizaOfertaForms;

import com.ceasa.digital.Forms.cadastraOfertaForm;
import com.ceasa.digital.Forms.encerraOfertaForm;
import com.ceasa.digital.Model.ofertaModel;

import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.ofertaService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/oferta")
public class ofertaController {

    @Autowired
    ofertaService oService;


    @GetMapping
    public ResponseEntity<List<ofertaModel>> recuperaOfertas(){

        

        return ResponseEntity.status(200).body(oService.recuperaOfertas());
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<Optional<ofertaModel>>> recuperaOfertasVendedor(@PathVariable int idVendedor){

        

        return ResponseEntity.status(200).body(oService.recuperaOfertasPorVendedor(idVendedor));
    }
    
    @PostMapping
    public ResponseEntity<Object> cadastraOfertas(@Valid @RequestBody cadastraOfertaForm oModel){

        try{
            
            httpResponses Response = oService.cadastrarOferta(oModel.getIdProduto(), oModel.getIdUnMedida(), oModel.getIdVendedor(), oModel.getQtdDisponivel(), oModel.getPesoUnMedida(), oModel.getVlUnMedida());
            
            return Response.responseProcess();
           

        }catch(Exception ex){

            return ResponseEntity.status(501).body(ex.getMessage());
        }

 
    }

  
    @PostMapping("/encerraoferta")
    public ResponseEntity<Object> encerraOfertas(@Validated @RequestBody encerraOfertaForm oModel){


        try{
            httpResponses Response = oService.encerrarOferta(oModel.getId());

            return Response.responseProcess();
            
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }

    @PostMapping("/ativaroferta")
    public ResponseEntity<Object> ativaroferta(@Validated @RequestBody encerraOfertaForm oModel){


        try{
            httpResponses Response = oService.ativarOferta(oModel.getId());

            return Response.responseProcess();
            
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }

   
    @PatchMapping
    public ResponseEntity<Object> atualizaOferta(@Validated @RequestBody atualizaOfertaForms oModel){


        try{


            httpResponses Response = oService.atualizarOferta(oModel);

            return Response.responseProcess();
            
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }
}
