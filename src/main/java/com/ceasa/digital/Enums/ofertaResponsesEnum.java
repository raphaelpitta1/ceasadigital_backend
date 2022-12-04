package com.ceasa.digital.Enums;

import com.ceasa.digital.services.httpResponses;

public enum ofertaResponsesEnum {
    oCadastrado(200,"Oferta Cadastrada com Sucesso"),
    oJacadastrado(422,"Oferta já Existente"), 
    oProblem(503,"Erro ao Processar Ação. Tente mais tarde!"),
    o_Nencontrado(422,"Oferta não encontrada"), 
    oUpdate(200,"Oferta Atualizada com Sucesso"),
    oJaDesativado(422, "Essa oferta já foi encerrada, não é necessário fazer essa ação novamente"),
    oAtivada(200,"Oferta foi reativada com Sucesso"),
    oJaAtivada(422, "Essa oferta já está ativada, não é necessário fazer essa ação novamente"),
    oDesativadoAcao(422, "Ação não permitida, a oferta esta encerrada"),
    oDesativado(200, "Oferta Encerrada com Sucesso");

    private final String message;
    private final int status_code;

    ofertaResponsesEnum(int status_code, String message) {
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
