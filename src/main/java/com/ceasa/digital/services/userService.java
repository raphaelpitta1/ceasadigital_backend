package com.ceasa.digital.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ceasa.digital.EnumsUsers.userResponsesEnum;
import com.ceasa.digital.EnumsUsers.userStatusEnum;
import com.ceasa.digital.EnumsUsers.userTipoPessoaEnum;
import com.ceasa.digital.EnumsUsers.userTipoUsuarioEnum;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.Repository.userRepository;


@Service
public class userService {
  

    @Autowired
    private userRepository uRepository;

    public httpResponses cadastrarUsuario(String nome, String sobrenome, String tipo_pessoa, String documento, String senha,
    String telefone){

        try{

        userModel uModel = new userModel();

            
            uModel.setNome(nome);
            uModel.setSobrenome(sobrenome);
            uModel.setTipo_pessoa(userTipoPessoaEnum.valueOf(tipo_pessoa.toUpperCase()).getValueString());
            uModel.setTipo_usuario(userTipoUsuarioEnum.Tipo_1.getValueString());
            uModel.setDocumento(documento);
            uModel.setTelefone(telefone);
            uModel.setSenha(senha);
            uModel.setTelefone(telefone);
            uModel.setStatus(true);
            

      

            Optional<userModel> validaExistenciaPessoa =uRepository.findByDocumento(uModel.getDocumento().toString());

            if(!validaExistenciaPessoa.isEmpty()){


                return userResponsesEnum.uJacadastrado.getResponseObject();

            }

        

        

        uRepository.save(uModel);

        return userResponsesEnum.uCadastrado.getResponseObject();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return userResponsesEnum.uProblem.getResponseObject();

        }

    }

    public List<userModel> recuperaUsuarios(){
        
        List<userModel> users = uRepository.findAll();
    
        
        return users;


    }
    public httpResponses atualizaUsuario(String nome, String sobrenome, String documento,
    String telefone){

        try{

     
        Optional<userModel> atualizaPessoa = uRepository.findByDocumento(documento);
        if(!atualizaPessoa.isEmpty()){


        atualizaPessoa.get().setNome(nome);
        atualizaPessoa.get().setSobrenome(sobrenome);
        atualizaPessoa.get().setTelefone(telefone);


        uRepository.save(atualizaPessoa.get());
        return userResponsesEnum.uUpdate.getResponseObject(); 

        }else{

            return userResponsesEnum.u_Nencontrado.getResponseObject(); 
        }
        
    }catch(Exception ex) {


        return userResponsesEnum.uProblem.getResponseObject();
    }

       
    }



    public httpResponses mudarStatusUsuario(String documento, Boolean status){
        


        try{
            String novoStatus = userStatusEnum.valueOf(status.toString().toUpperCase()).getValue();

            Optional<userModel> atualizaStatusPessoa = uRepository.findByDocumento(documento);
            
           if(!atualizaStatusPessoa.isEmpty()){

            atualizaStatusPessoa.get().setStatus(Boolean.parseBoolean(novoStatus));
            uRepository.save(atualizaStatusPessoa.get());

            return userResponsesEnum.uDesativado.getResponseObject();
            }else{


                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        }catch(Exception ex){

           return userResponsesEnum.uProblem.getResponseObject();
        }
    }
}
