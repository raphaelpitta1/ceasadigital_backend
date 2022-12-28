package com.ceasa.digital.Controller;


import java.util.List;
import java.util.Optional;

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
import com.ceasa.digital.Forms.loginUsuarioForm;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.services.httpResponses;
import com.ceasa.digital.services.httpSimulaLoginResponses;
import com.ceasa.digital.services.userService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/usuarios")
public class userController {

    @Autowired
    userService uService;


    @GetMapping
    public ResponseEntity<List<userModel>> selectUsuarios(){

        

        return ResponseEntity.status(200).body(uService.recuperaUsuarios());
    }

    @GetMapping("/{documento}")
    public ResponseEntity<Optional<userModel>> selectUsuariosDocumento(@PathVariable String documento){

        

        return ResponseEntity.status(200).body(uService.recuperaUsuariobyDocumento(documento));
    }


   
    
    @PostMapping("/cadastro")
    public ResponseEntity<Object> cadastraUsuario(@Validated @RequestBody cadastraUsuarioForm umModel){

        try{
            
          httpResponses Response = uService.cadastrarUsuario(umModel.getNome(), umModel.getSobrenome(), umModel.getTipo_pessoa().toString(), umModel.getDocumento(),umModel.getSenha(), umModel.getTelefone(), umModel.getCep(), umModel.getLatitude(), umModel.getLongitude());
           
          return Response.responseProcess();
          //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

        }catch(Exception ex){

            return ResponseEntity.status(501).body(ex.getMessage());
        }

 
    }

    @PatchMapping
    public ResponseEntity<Object> atualizaUsuario(@Validated @RequestBody atualizaUsuarioForm umModel) {

try{
    httpResponses Response = uService.atualizaUsuario(umModel.getNome(), umModel.getSobrenome(), umModel.getDocumento(),umModel.getTelefone(), umModel.getCep(), umModel.getLatitude(), umModel.getLongitude());
    
    
    return Response.responseProcess();
   // return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());

}catch(Exception ex){

    return ResponseEntity.status(501).body(ex.getMessage());

}
      
    }

    @PatchMapping("/status")
    public ResponseEntity<Object> mudarstatus(@Validated @RequestBody atualizaStatusUsuarioForm umModel){


        try{
            httpResponses Response = uService.mudarStatusUsuario(umModel.getDocumento(), umModel.isStatus());

            return Response.responseProcess();
            //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
        
        }catch(Exception ex){
        
            return ResponseEntity.status(501).body(ex.getMessage());
        
        }


    }
    


    @PatchMapping("api/usuarios/esqueciminhasenha")
    public ResponseEntity<Object> atualizaSenha(@Validated @RequestBody atualizaSenhaUsuarioForm umModel){


    try{
        httpResponses Response = uService.autalizaSenha(umModel.getDocumento(), umModel.getSenha());

        return Response.responseProcess();
        //return ResponseEntity.status(Response.getStatusCode()).body(Response.getMessage());
    
    }catch(Exception ex){
    
        return ResponseEntity.status(501).body(ex.getMessage());
    
    }


}

}


