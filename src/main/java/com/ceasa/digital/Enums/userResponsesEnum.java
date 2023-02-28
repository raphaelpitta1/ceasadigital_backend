package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum userResponsesEnum {
    uCadastrado(200,"Usuário Cadastrado com sucesso"),
    eCadastrado(200,"Endereço Cadastrado com sucesso"),
    eJacadastrado(422,"Usuário já Possui esse endereço"), 
    uJacadastrado(422,"Usuário já Possui Cadastro"), 
    uProblem(503,"Erro ao processar ação. Tente mais tarde!"),
    u_Nencontrado(422,"Usuário não encontrado"), 
    uUpdate(200,"Usuário atualizado com sucesso"),
    uWhatsSenhaUpdate(200,"Caso os seus dados estejam corretos, você receberá uma mensagem em seu whatsapp com a senha de acesso"),
    uDesativado(200, "Usuário desativado com sucesso"),
    uLogado(200, "Logado com sucesso"),
    uSenhaIncorreta(422, "Senha incorreta"),
    uAtivado(200, "Usuário ativado com sucesso");

    private final String message;
    private final int status_code;

    userResponsesEnum(int status_code, String message) {
        this.message = message;
        this.status_code = status_code;
    }
 
    public String getMessage() {
        return message;
    }

    public int getStatus_code() {
        return status_code;
    }

    public httpResponses getResponseObject() {
        httpResponses hResponses = new httpResponses();
        hResponses.setMessage(getMessage());
        hResponses.setStatusCode(getStatus_code());
        return hResponses;
    }


    
    
}
