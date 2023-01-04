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

import com.ceasa.digital.Forms.produtoForm;
import com.ceasa.digital.Model.produtoModel;

import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.produtoService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/produtos")
public class produtoController {

    @Autowired
    produtoService pService;

    @GetMapping
    public ResponseEntity<List<produtoModel>> selectProdutos() {

        return ResponseEntity.status(200).body(pService.recuperaProdutos());
    }

    @PostMapping
    public ResponseEntity<Object> cadastraProduto(@Valid @RequestBody produtoForm umModel) {

        try {

            httpResponses Response = pService.cadastrarProduto(umModel.getNome(), umModel.getCategoria());
            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduto(@Validated @RequestBody produtoForm umModel) {

        try {
            httpResponses Response = pService.deletarProduto(umModel.getNome());
            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());

        }

    }

    @PatchMapping
    public ResponseEntity<Object> atualizaProduto(@Validated @RequestBody produtoForm umModel) {

        try {
            httpResponses Response = pService.atualizarProduto(umModel.getNome());
            return Response.responseProcess();
            // /return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());

        }

    }
}
