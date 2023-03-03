package com.ceasa.digital.Forms;


public class editaVendaForm {
 
    private int idVenda;

    private int qtd;

    public editaVendaForm() {
    }

    public editaVendaForm(int idVenda, int qtd) {
        this.idVenda = idVenda;
        this.qtd = qtd;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    
        
}
