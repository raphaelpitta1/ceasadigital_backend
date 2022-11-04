package com.ceasa.digital.EnumsUsers;

import com.ceasa.digital.services.httpResponses;

public enum userResponsesEnum {
    uCadastrado(200,"Usuário Cadastrado com Sucesso"),
    uJacadastrado(422,"Usuário já Possui Cadastro"), 
    uProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    u_Nencontrado(422,"Usuário não encontrado"), 
    uUpdate(200,"Usuário Atualizado com Sucesso"),
    uDesativado(200, "Usuário Desativado com Sucesso");

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
