package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum produtoResponsesEnum {
    pCadastrado(200,"Produto Cadastrado com Sucesso"),
    pJacadastrado(422,"Produto já Possui Cadastro"), 
    pProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    p_Nencontrado(422,"Produto não encontrado"), 
    pUpdate(200,"Produto Atualizado com Sucesso"),
    pDesativado(200, "Produto Deletado com Sucesso");

    private final String message;
    private final int status_code;

    produtoResponsesEnum(int status_code, String message) {
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
