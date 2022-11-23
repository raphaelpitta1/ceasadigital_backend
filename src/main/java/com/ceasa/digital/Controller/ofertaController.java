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

import com.ceasa.digital.Forms.atualizaQtdDisponivelOfertaForms;
import com.ceasa.digital.Forms.atualizaVlUnMedidaOfertaForms;
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

    @GetMapping("/{idVendedor}")
    public ResponseEntity<List<Optional<ofertaModel>>> recuperaOfertasVendedor(@PathVariable int idVendedor){

        

        return ResponseEntity.status(200).body(oService.recuperaOfertasPorVendedor(idVendedor));
    }
    
    @PostMapping
    public ResponseEntity<Object> cadastraOfertas(@Valid @RequestBody cadastraOfertaForm oModel){

        try{
            
            httpResponses Response = oService.cadastrarOferta(oModel.getIdProduto(), oModel.getIdUnMedida(), oModel.getIdVendedor(), oModel.getQtdDisponivel(), oModel.getPesoUnMedida(), oModel.getVlUnMedida());
            
            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        }catch(Exception ex){

            return ResponseEntity.status(501).body(ex.getMessage());
        }

 
    }

  
    @PostMapping("/encerraOferta")
    public ResponseEntity<Object> encerraOfertas(@Validated @RequestBody encerraOfertaForm oModel){


        try{
            httpResponses Response = oService.encerrarOferta(oModel.getId());

            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }

    @PatchMapping("/quantidade")
    public ResponseEntity<Object> atualizaQtdDisponivelOferta(@Validated @RequestBody atualizaQtdDisponivelOfertaForms oModel){


        try{


            httpResponses Response = oService.atualizarQtdDisponivelOferta(oModel.getId(), oModel.getQtd());

            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }


    @PatchMapping("/valorUnidade")
    public ResponseEntity<Object> atualizaValorUnMedidaOfertas(@Validated @RequestBody atualizaVlUnMedidaOfertaForms oModel){


        try{

            
            httpResponses Response = oService.atualizarValorUnMedidaOferta(oModel.getId(), oModel.getVlUnMedida());
            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }
}
