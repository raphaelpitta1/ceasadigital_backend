package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum unMedidaResponsesEnum {
    pCadastrado(200,"Unidade de Medida Cadastrada com Sucesso"),
    pJacadastrado(422,"Unidade de Medida já Possui Cadastro"), 
    pProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    p_Nencontrado(422,"Unidade de Medida não encontrada"), 
    pUpdate(200,"Unidade de Medida Atualizada com Sucesso"),
    pDesativado(200, "Unidade de Medida com Sucesso");

    private final String message;
    private final int status_code;

    unMedidaResponsesEnum(int status_code, String message) {
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
