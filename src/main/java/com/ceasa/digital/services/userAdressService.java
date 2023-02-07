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

           
            
        

        
            Optional<userAdressModel> validaExistenciaEndereco = uRepository.findByidUsuarioAndNumeroAndCep(user.getId(), user.getNumero(), user.getCep());


            if (!validaExistenciaEndereco.isEmpty()) {

                return userResponsesEnum.eJacadastrado.getResponseObject();

            }
           
            uRepository.save(user);

            return userResponsesEnum.eCadastrado.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return userResponsesEnum.uProblem.getResponseObject();

        }

    }

    public Optional<userAdressModel> recuperaEnderecoByIdUsuario(int idUsuario) {

    Optional<userAdressModel> users = uRepository.findByidUsuario(idUsuario).get(0);

        return users;

    }

    public List<Optional<userAdressModel>> recuperaEnderecosProdutores() {

        

             return uRepository.findEndProdutores();

      

    }

    
    
    public httpResponses atualizaEnderecoByIdUsuario(userAdressModel uModel) {

        try {

            Optional<userAdressModel> atualizaEndereco = uRepository.findByidUsuarioAndNumeroAndCep(uModel.getIdUsuario(),uModel.getNumero(), uModel.getCep());
            if (!atualizaEndereco.isEmpty()) {

                atualizaEndereco.get().setBairro(uModel.getBairro());
                atualizaEndereco.get().setCep(uModel.getCep());
                atualizaEndereco.get().setCidade(uModel.getCidade());
                atualizaEndereco.get().setIdUsuario(uModel.getIdUsuario());
                atualizaEndereco.get().setLatitude(uModel.getLatitude());
                atualizaEndereco.get().setLongitude(uModel.getLatitude());
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


