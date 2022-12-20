package com.ceasa.digital.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.userResponsesEnum;
import com.ceasa.digital.Enums.userStatusEnum;
import com.ceasa.digital.Enums.userTipoPessoaEnum;
import com.ceasa.digital.Enums.userTipoUsuarioEnum;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.Repository.userRepository;

@Service
public class userService {

    @Autowired
    private userRepository uRepository;
    @Autowired
    private whatasappService wService;

    public httpResponses cadastrarUsuario(String nome, String sobrenome, String tipo_pessoa, String documento,
            String senha,
            String telefone, String cep, String latitude, String longitude) {

        try {

            userModel uModel = new userModel();

            uModel.setNome(nome);
            uModel.setSobrenome(sobrenome);
            uModel.setTipo_pessoa(userTipoPessoaEnum.valueOf(tipo_pessoa.toUpperCase()).getValueString());
            uModel.setTipo_usuario(userTipoUsuarioEnum.Tipo_1.getValueString());
            uModel.setDocumento(documento);
            uModel.setTelefone(telefone);
            uModel.setSenha(senha);
            uModel.setTelefone(telefone);
            uModel.setCep(cep);
            uModel.setLatitude(latitude);
            uModel.setLongitude(longitude);

            Optional<userModel> validaExistenciaPessoa = uRepository.findByDocumento(uModel.getDocumento().toString());

            if (!validaExistenciaPessoa.isEmpty()) {

                return userResponsesEnum.uJacadastrado.getResponseObject();

            }
            wService.setNumber("55"+telefone);
            wService.setMessage("Ola, "+ nome +". \r\nEstamos muito felizes que se juntou ao Ceasa Digital :)\r\n\r\nSeja muito bem-vindo!\r\n\r\nAtenciosamente,\r\nEquipe Ceasa Digital 👋");
            //wService.sendMessage();
            uRepository.save(uModel);

            return userResponsesEnum.uCadastrado.getResponseObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return userResponsesEnum.uProblem.getResponseObject();

        }

    }

    public List<userModel> recuperaUsuarios() {

        List<userModel> users = uRepository.findAll();

        return users;

    }

    public Optional<userModel> recuperaUsuariobyDocumento(String documento) {

        Optional<userModel> users = uRepository.findByDocumento(documento);

        return users;

    }

    public httpSimulaLoginResponses simulaLogin(String documento, String senha) {
        httpSimulaLoginResponses loginResponses = new httpSimulaLoginResponses();
        
        Optional<userModel> users = uRepository.findByDocumento(documento);
        if(users.isEmpty()){
            loginResponses.setMessage(userResponsesEnum.u_Nencontrado.getMessage());
            loginResponses.setStatusCode(userResponsesEnum.u_Nencontrado.getStatus_code());
            return loginResponses;
        }else{

                if(users.get().getSenha().equals(senha)){
                    loginResponses.setMessage(userResponsesEnum.uLogado.getMessage());
                    loginResponses.setStatusCode(userResponsesEnum.uLogado.getStatus_code());
                    loginResponses.setIdUsuario(users.get().getId());
                    loginResponses.setNome(users.get().getNome());
                    loginResponses.setToken("em desenvolvimento");
                    return loginResponses;

                }else{

                    loginResponses.setMessage(userResponsesEnum.uSenhaIncorreta.getMessage());
                    loginResponses.setStatusCode(userResponsesEnum.uSenhaIncorreta.getStatus_code());
                        return loginResponses;


                }
            
        }
       
    }

    public httpResponses atualizaUsuario(String nome, String sobrenome,String documento,
            String telefone, String cep, String latitude, String longitude) {

        try {

            Optional<userModel> atualizaPessoa = uRepository.findByDocumento(documento);
            if (!atualizaPessoa.isEmpty()) {

                atualizaPessoa.get().setNome(nome);
                atualizaPessoa.get().setSobrenome(sobrenome);
                atualizaPessoa.get().setTelefone(telefone);

                uRepository.save(atualizaPessoa.get());
                return userResponsesEnum.uUpdate.getResponseObject();

            } else {

                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        } catch (Exception ex) {

            return userResponsesEnum.uProblem.getResponseObject();
        }

    }

    public httpResponses mudarStatusUsuario(String documento, Boolean status) {

        try {
            String novoStatus = userStatusEnum.valueOf(status.toString().toUpperCase()).getValueString();

            Optional<userModel> atualizaStatusPessoa = uRepository.findByDocumento(documento);

            if (!atualizaStatusPessoa.isEmpty()) {

                atualizaStatusPessoa.get().setStatus(Boolean.parseBoolean(novoStatus));
                uRepository.save(atualizaStatusPessoa.get());
                if (Boolean.parseBoolean(novoStatus)) {
                    return userResponsesEnum.uAtivado.getResponseObject();

                } else {
                    return userResponsesEnum.uDesativado.getResponseObject();

                }

            } else {

                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        } catch (Exception ex) {

            return userResponsesEnum.uProblem.getResponseObject();
        }
    }

    public httpResponses autalizaSenha(String documento, String senha) {

        try {

            Optional<userModel> atualizaStatusPessoa = uRepository.findByDocumento(documento);

            if (!atualizaStatusPessoa.isEmpty()) {

                atualizaStatusPessoa.get().setSenha(senha);
                ;
                uRepository.save(atualizaStatusPessoa.get());

                return userResponsesEnum.uUpdate.getResponseObject();
            } else {

                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        } catch (Exception ex) {

            return userResponsesEnum.uProblem.getResponseObject();
        }
    }
}
