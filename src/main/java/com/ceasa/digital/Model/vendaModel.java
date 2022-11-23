package com.ceasa.digital.Model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity(name="Venda")
public class vendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idOferta;
    private int idVendedor;
    private int idComprador;
    private int qtd_comprada;
    private float valor_transacao;
    private int avaliacao_vendedor=0;
    private int avaliacao_comprador=0;
    private String vendaStatus="PROCESSO";

    @CreationTimestamp 
    private Timestamp createDate;
    @UpdateTimestamp 
    private Timestamp updateDate;
    
    public vendaModel() {
    }



    public vendaModel(int idOferta, int idComprador, int qtd_comprada) {
        this.idOferta = idOferta;
        this.idComprador = idComprador;
        this.qtd_comprada = qtd_comprada;
    }


    



    public String getVendaStatus() {
        return vendaStatus;
    }



    public void setVendaStatus(String vendaStatus) {
        this.vendaStatus = vendaStatus;
    }



    public int getId() {
        return id;
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

    public int getIdVendedor() {
        return idVendedor;
    }


    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }



    public int getQtd_comprada() {
        return qtd_comprada;
    }


    public void setQtd_comprada(int qtd_comprada) {
        this.qtd_comprada = qtd_comprada;
    }


    public float getValor_transacao() {
        return valor_transacao;
    }


    public void setValor_transacao(float valor_unidade, int qtd_comprada) {
        this.valor_transacao = valor_unidade * qtd_comprada;
    }


    public int getAvaliacao_vendedor() {
        return avaliacao_vendedor;
    }


    public void setAvaliacao_vendedor(int avaliacao_vendedor) {
        this.avaliacao_vendedor = avaliacao_vendedor;
    }


    public int getAvaliacao_comprador() {
        return avaliacao_comprador;
    }


    public void setAvaliacao_comprador(int avaliacao_comprador) {
        this.avaliacao_comprador = avaliacao_comprador;
    }



    public Timestamp getCreateDate() {
        return createDate;
    }



    public Timestamp getUpdateDate() {
        return updateDate;
    }




 


    

    
}
