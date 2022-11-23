package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum negociacaoResponsesEnum {
    nIniciada(200,"Negociação iniciada com Sucesso"),
    nJaExistente(422,"Negociação já Existente"), 
    nProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    n_Nencontrado(422,"Negociação não encontrada"), 
    nUpdate(200,"Negociação Atualizada com Sucesso"),
    nDesativado(200, "Negociação Cancelada com Sucesso"),
    nCancelado(200, "Operação Inválida: A negociação está com o status CANCELADO"),
    nDesativadoPendenteVendedor(200, "O Cancelamento está pendende de aprovação pelo vendedor"),
    nDesativadoPendenteComprador(200, "O Cancelamento está pendende de aprovação pelo comprador"),
    nConcluidaPendenteVendedor(200, "A Conclusão da negociação precisa de aprovação do vendedor"),
    nConcluidaPendenteComprador(200, "A Conclusão da negociação precisa de aprovação do comprador"),
    nConcluida(200, "Negociação concluída com sucesso"),
    nJaConcluida(200, "Operação Inválida: A negociação está com o status CONLCUIDO");

    private final String message;
    private final int status_code;

    negociacaoResponsesEnum(int status_code, String message) {
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
