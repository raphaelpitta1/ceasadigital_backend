package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum vendaResponsesEnum {
    vCadastrado(200,"Venda Cadastrada com Sucesso"),
    vJacadastrado(422,"Venda já Existente"), 
    vFraude(422,"Você não pode comprar seus prórios produtos"), 
    vProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    v_Nencontrado(422,"Venda não encontrada"), 
    qtd_indisponivel(422, "Quantidade Indisponível em estoque"),
    vUpdate(200,"Venda Atualizada com Sucesso"),
    vDesativado(200, "Venda Deletada com Sucesso");

    private final String message;
    private final int status_code;

    vendaResponsesEnum(int status_code, String message) {
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
