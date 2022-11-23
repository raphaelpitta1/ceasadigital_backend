package com.ceasa.digital.Forms;


public class cadastraOfertaForm {
 
    private int idProduto;
    private int idUnMedida;
    private int idVendedor;
    private int qtdDisponivel;
    private float pesoUnMedida;
    private float vlUnMedida;
    public cadastraOfertaForm( int idProduto,
           int idUnMedida,
           int idVendedor,
           int qtdDisponivel,
           float pesoUnMedida,
            float vlUnMedida) {
        this.idProduto = idProduto;
        this.idUnMedida = idUnMedida;
        this.idVendedor = idVendedor;
        this.qtdDisponivel = qtdDisponivel;
        this.pesoUnMedida = pesoUnMedida;
        this.vlUnMedida = vlUnMedida;
    }
    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    public int getIdUnMedida() {
        return idUnMedida;
    }
    public void setIdUnMedida(int idUnMedida) {
        this.idUnMedida = idUnMedida;
    }
    public int getIdVendedor() {
        return idVendedor;
    }
    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    public int getQtdDisponivel() {
        return qtdDisponivel;
    }
    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }
    public float getPesoUnMedida() {
        return pesoUnMedida;
    }
    public void setPesoUnMedida(float pesoUnMedida) {
        this.pesoUnMedida = pesoUnMedida;
    }
    public float getVlUnMedida() {
        return vlUnMedida;
    }
    public void setVlUnMedida(float vlUnMedida) {
        this.vlUnMedida = vlUnMedida;
    }



    
}
