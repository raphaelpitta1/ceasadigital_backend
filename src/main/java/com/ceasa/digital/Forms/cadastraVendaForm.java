package com.ceasa.digital.Forms;


public class cadastraVendaForm {
 
    private int idOferta;
    private int idComprador; 
    private int qtd;

    
    public cadastraVendaForm(int idOferta, int idComprador, int qtd) {
        this.idOferta = idOferta;
        this.idComprador = idComprador;
        this.qtd = qtd;
    }


    public int getIdOferta() {
        return idOferta;
    }


    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }


    public int getIdComprador() {
        return idComprador;
    }


    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }


    public int getQtd() {
        return qtd;
    }


    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
   
    

    
}
