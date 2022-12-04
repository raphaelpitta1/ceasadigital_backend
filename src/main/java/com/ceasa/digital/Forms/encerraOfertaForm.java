package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;

public class encerraOfertaForm {


    
    private int id;


    public encerraOfertaForm(@NotBlank(message = "Id da oferta é um campo obrigatório") int id) {
        this.id = id;
    }


    
    public encerraOfertaForm() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 


    

    
}
