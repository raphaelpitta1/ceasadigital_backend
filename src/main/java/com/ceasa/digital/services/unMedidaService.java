package com.ceasa.digital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.unMedidaResponsesEnum;
import com.ceasa.digital.Model.unMedidaModel;
import com.ceasa.digital.Repository.unMedidaRepository;


@Service
public class unMedidaService {
    
    @Autowired
    private unMedidaRepository unRepository;

    public httpResponses cadastrarUnMedida(String nome){

        try{

        unMedidaModel unModel = new unMedidaModel();

            
        unModel.setNome(nome);
            

      
Optional<unMedidaModel> validaExistenciaUnMedida = unRepository.findByNome(unModel.getNome().toString());

            if(!validaExistenciaUnMedida.isEmpty()){


                return unMedidaResponsesEnum.pJacadastrado.getResponseObject();

            }

        

        

        unRepository.save(unModel);

        return unMedidaResponsesEnum.pCadastrado.getResponseObject();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return unMedidaResponsesEnum.pProblem.getResponseObject();

        }

    }

    public List<unMedidaModel> recuperaUnMedidas(){
        
        List<unMedidaModel> UnMedidas = unRepository.findAll();
    
        
        return UnMedidas;


    }
    
    public httpResponses deletarUnMedida(String nome){

        try{

        unMedidaModel unModel = new unMedidaModel();

            
        unModel.setNome(nome);
            

      
Optional<unMedidaModel> validaExistenciaUnMedida = unRepository.findByNome(unModel.getNome().toString());

            if(validaExistenciaUnMedida.isEmpty()){


                return unMedidaResponsesEnum.p_Nencontrado.getResponseObject();

            }

        

        

        unRepository.delete(validaExistenciaUnMedida.get());

        return unMedidaResponsesEnum.pDesativado.getResponseObject();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return unMedidaResponsesEnum.pProblem.getResponseObject();

        }

    }

    public httpResponses atualizarUnMedida(String nome){

        try{

            unMedidaModel unModel = new unMedidaModel();

            
        unModel.setNome(nome);
            

      
Optional<unMedidaModel> validaExistenciaUnMedida = unRepository.findByNome(unModel.getNome().toString());

            if(validaExistenciaUnMedida.isEmpty()){


                return unMedidaResponsesEnum.p_Nencontrado.getResponseObject();

            }

        

        

        unRepository.save(validaExistenciaUnMedida.get());

        return unMedidaResponsesEnum.pUpdate.getResponseObject();

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return unMedidaResponsesEnum.pProblem.getResponseObject();

        }

    }

}
