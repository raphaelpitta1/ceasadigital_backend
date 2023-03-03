package com.ceasa.digital.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceasa.digital.Forms.cadastraVendaForm;
import com.ceasa.digital.Forms.cancelarConcluirVendaForm;
import com.ceasa.digital.Forms.editaVendaForm;
import com.ceasa.digital.Model.vendaModel;
import com.ceasa.digital.services.httpResponses;

import com.ceasa.digital.services.vendaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/venda")
public class vendaController {

    @Autowired
    vendaService vService;

    @GetMapping
    public ResponseEntity<List<vendaModel>> recuperaVendas() {

        return ResponseEntity.status(200).body(vService.recuperaVendas());
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<Optional<vendaModel>>> recuperarVendasVendedor(@PathVariable int idVendedor) {

        return ResponseEntity.status(200).body(vService.recuperaVendasVendedor(idVendedor));
    }

    @GetMapping("/comprador/{idComprador}")
    public ResponseEntity<List<Optional<vendaModel>>> recuperarVendasComprador(@PathVariable int idComprador) {

        return ResponseEntity.status(200).body(vService.recuperaComprasComprador(idComprador));
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarVenda(@Valid @RequestBody cadastraVendaForm vModel) {


        try {

            httpResponses Response = vService.fazerVenda(vModel.getIdOferta(), vModel.getIdComprador(),
                    vModel.getQtd());

            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }

    }

    @PatchMapping
    public ResponseEntity<Object> editarVenda(@Valid @RequestBody editaVendaForm vModel) {


        try {

            httpResponses Response = vService.editarVenda(vModel.getIdVenda(),
                    vModel.getQtd());

            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }

    }
   
    @PostMapping("/cancelamento")
    public ResponseEntity<Object> cancelarVenda(@Valid @RequestBody cancelarConcluirVendaForm vModel) {

        try {

            httpResponses Response = vService.processoCancelamento(vModel.getIdVenda(), vModel.getIdUsuario());

            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }

    }

    @PostMapping("/conclusao")
    public ResponseEntity<Object> conclusao(@Valid @RequestBody cancelarConcluirVendaForm vModel) {

        try {

            httpResponses Response = vService.processoConclusao(vModel.getIdVenda(), vModel.getIdUsuario());

            return Response.responseProcess();
            // return
            // ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }
    }
}
