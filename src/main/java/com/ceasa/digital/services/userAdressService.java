package com.ceasa.digital.services;


import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.userResponsesEnum;

import com.ceasa.digital.Model.userAdressModel;
import com.ceasa.digital.Repository.userAdressRepository;


@Service
public class userAdressService {
    
        
    @Autowired
    private userAdressRepository uRepository;



    public httpResponses cadastrarEndereco(userAdressModel user) {

        try {
            if (!uRepository.findByidUsuarioAndNumeroAndCep(user.getId(), user.getNumero(), user.getCep()).isEmpty()) {

                return userResponsesEnum.eJacadastrado.getResponseObject();

            }
            
            uRepository.save(user);

            return userResponsesEnum.eCadastrado.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return userResponsesEnum.uProblem.getResponseObject();

        }

    }

    public userAdressModel recuperaEnderecoByIdUsuario(int idUsuario) {
    
    List<Optional<userAdressModel>> users = uRepository.findByidUsuario(idUsuario);

    if(users.isEmpty()){

        userAdressModel user2 = new userAdressModel();
        return user2;
    }else{


        return users.get(0).get();
    }
        

    }

    public List<Optional<userAdressModel>> recuperaEnderecosProdutores() {

        

             return uRepository.findEndProdutores();

      

    }

    
    
    public httpResponses atualizaEnderecoByIdUsuario(userAdressModel uModel) {

        try {

            if (!uRepository.findByidUsuario(uModel.getIdUsuario()).isEmpty()) {

                Optional<userAdressModel> atualizaEndereco = uRepository.findByidUsuario(uModel.getIdUsuario()).get(0);
                atualizaEndereco.get().setBairro(uModel.getBairro());
                atualizaEndereco.get().setCep(uModel.getCep());
                atualizaEndereco.get().setCidade(uModel.getCidade());
                atualizaEndereco.get().setIdUsuario(uModel.getIdUsuario());
                atualizaEndereco.get().setLatitude(uModel.getLatitude());
                atualizaEndereco.get().setLongitude(uModel.getLongitude());
                atualizaEndereco.get().setLogradouro(uModel.getLogradouro());
                atualizaEndereco.get().setNumero(uModel.getNumero());
                atualizaEndereco.get().setUF(uModel.getUF());
               

                uRepository.save(atualizaEndereco.get());
                return userResponsesEnum.uUpdate.getResponseObject();

            } else {

                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        } catch (Exception ex) {

            return userResponsesEnum.uProblem.getResponseObject();
        }

    }

 


        
    
}


