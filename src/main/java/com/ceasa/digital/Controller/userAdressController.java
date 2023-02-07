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

import com.ceasa.digital.Forms.atualizaSenhaUsuarioForm;
import com.ceasa.digital.Forms.atualizaStatusUsuarioForm;
import com.ceasa.digital.Forms.atualizaUsuarioForm;
import com.ceasa.digital.Forms.cadastraUsuarioForm;
import com.ceasa.digital.Model.userAdressModel;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.userAdressService;
import com.ceasa.digital.services.userService;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/endereco")
public class userAdressController {

    @Autowired
    userAdressService uService;

     @GetMapping
    public ResponseEntity<Optional<userAdressModel>> selectAdressProdutores() {

        return ResponseEntity.status(200).body(uService.recuperaEnderecosProdutores());
    }

 

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<userAdressModel>> selectEnderecoUsuariosbyId(@PathVariable int id) {

        return ResponseEntity.status(200).body(uService.recuperaEnderecoByIdUsuario(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastraEnderecoUsuario(@Valid @RequestBody userAdressModel uAdressModel) {

        try {
    
            httpResponses Response = uService.cadastrarEndereco(uAdressModel);

            return Response.responseProcess();
        
        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());
        }

    }

    @PatchMapping
    public ResponseEntity<Object> atualizaEnderecosuario(@Valid @RequestBody userAdressModel uAdressModel) {

        try {
            httpResponses Response = uService.atualizaEnderecoByIdUsuario(uAdressModel);
            return Response.responseProcess();
       

        } catch (Exception ex) {

            return ResponseEntity.status(501).body(ex.getMessage());

        }

    }

}
