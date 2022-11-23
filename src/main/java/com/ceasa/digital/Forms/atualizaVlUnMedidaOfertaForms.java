package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;

public class atualizaVlUnMedidaOfertaForms {


    @NotBlank(message = "Id da oferta é um campo obrigatório")    
    private int id;


    @NotBlank(message = "Valor da unidade de medida é um campo obrigatório nesta operação")    
    private float vlUnMedida;


    public atualizaVlUnMedidaOfertaForms(@NotBlank(message = "Id da oferta é um campo obrigatório") int id,
            @NotBlank(message = "Valor da unidade de medida é um campo obrigatório nesta operação") float vlUnMedida) {
        this.id = id;
        this.vlUnMedida = vlUnMedida;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public float getVlUnMedida() {
        return vlUnMedida;
    }


    public void setVlUnMedida(float vlUnMedida) {
        this.vlUnMedida = vlUnMedida;
    }


    



    
}
