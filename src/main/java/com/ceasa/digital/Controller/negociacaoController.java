package com.ceasa.digital.Controller;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.ceasa.digital.Model.negociacaoModel;

import com.ceasa.digital.services.negociacaoService;



@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/negociacao")
public class negociacaoController {

    @Autowired
    negociacaoService nService;


    @GetMapping("/{idVenda}")
    public ResponseEntity<Optional<negociacaoModel>> negociacaoModel(@PathVariable int idVenda){

        

        return ResponseEntity.status(200).body(nService.recuperaNegociacao(idVenda));
    }
   
    
}
