package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;

public class atualizaQtdDisponivelOfertaForms {
 
 
@NotBlank(message = "Id da oferta é um campo obrigatório")    
private int id;
@NotBlank(message = "Quantidade é um campo obrigatório")    
private int qtd;




public atualizaQtdDisponivelOfertaForms(@NotBlank(message = "Id da oferta é um campo obrigatório") int id,
        @NotBlank(message = "Quantidade é um campo obrigatório") int qtd) {
    this.id = id;
    this.qtd = qtd;
}



public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public int getQtd() {
    return qtd;
}
public void setQtd(int qtd) {
    this.qtd = qtd;
}





}
