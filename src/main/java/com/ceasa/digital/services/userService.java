package com.ceasa.digital.services;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ceasa.digital.Enums.userResponsesEnum;
import com.ceasa.digital.Enums.userStatusEnum;
import com.ceasa.digital.Enums.userTipoPessoaEnum;
import com.ceasa.digital.Enums.userTipoUsuarioEnum;
import com.ceasa.digital.Model.userModel;
import com.ceasa.digital.Repository.userRepository;

@Service
public class userService implements UserDetailsService {

    @Autowired
    private userRepository uRepository;
    @Autowired
    private whatasappService wService;
    @Value("${phone.prefix}")
    private String phonePrefix;
    @Value("${app.link}")
    private URL appLink;
    

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
                System.out.println(phonePrefix);
            Optional<userModel> validaExistenciaPessoa = uRepository.findByDocumento(uModel.getDocumento().toString());

            if (!validaExistenciaPessoa.isEmpty()) {

                return userResponsesEnum.uJacadastrado.getResponseObject();

            }
            String message = "Ola, "+ nome +". \r\nEstamos muito felizes que se juntou ao Ceasa Digital :)\r\n\r\nSeja muito bem-vindo!\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
            
          
            wService.setNumber(phonePrefix+telefone);
            wService.setMessage(message);
            wService.sendMessage();
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

    
    public Optional<userModel> recuperaUsuariobyId(int id) {

        Optional<userModel> users = uRepository.findById(id);

        return users;

    }

    

    public httpResponses atualizaUsuario(int id, String nome, String telefone ,String senha) {

        try {

            Optional<userModel> atualizaPessoa = uRepository.findById(id);
            if (!atualizaPessoa.isEmpty()) {

                atualizaPessoa.get().setNome(nome);
                atualizaPessoa.get().setSenha(senha);
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

    public httpResponses recuperaSenha(String documento, String telefone) {

        try {

            Optional<userModel> atualizaStatusPessoa = uRepository.findByDocumento(documento);

            if (!atualizaStatusPessoa.isEmpty() && atualizaStatusPessoa.get().getTelefone().equals(telefone) ) {

                String message = "Ola, "+ atualizaStatusPessoa.get().getNome().toString() +". \r\nSegue sua senha de acesso: *"+atualizaStatusPessoa.get().getSenha().toString()+"* :)\r\n\r\n*Link do Aplicativo:*"+appLink+"\r\n\r\n\r\n\r\nAtenciosamente,\r\n*Equipe Ceasa Digital*"; 
            
          
                wService.setNumber(phonePrefix+atualizaStatusPessoa.get().getTelefone());
                wService.setMessage(message);
                wService.sendMessage();

                return userResponsesEnum.uWhatsSenhaUpdate.getResponseObject();
            } else {

                return userResponsesEnum.u_Nencontrado.getResponseObject();
            }

        } catch (Exception ex) {

            return userResponsesEnum.uProblem.getResponseObject();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String documento) throws UsernameNotFoundException {
        userModel users = uRepository.findByDocumento(documento).orElseThrow(()-> new UsernameNotFoundException("Login Inválido"));
      
       
        return User.builder().username(documento).password(users.getSenha()).roles("USER").build();

        
    }
}
