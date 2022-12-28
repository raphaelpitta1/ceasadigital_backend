package com.ceasa.digital.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity(name="Processo_Negociacao")
public class negociacaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int idVenda;
    @Column(length = 10)
    private String statusNegociacao;
    @Column(length = 100)
    private String subStatusNegociacao;

    private int qtd_comprada;

    private boolean aprovacao_vendedor;

    private boolean aprovacao_comprador;

    private boolean avisa_inicioNegociacao=false;

    private boolean avisa_NegociacaoPendente=false;

    private boolean avisa_PropostaCancelamento=false;

    private boolean avisa_Cancelamento=false;

    private boolean avisa_ConclusaoVenda=false;

    

  




    public boolean isAvisa_inicioNegociacao() {
        return avisa_inicioNegociacao;
    }



    public void setAvisa_inicioNegociacao(boolean avisa_inicioNegociacao) {
        this.avisa_inicioNegociacao = avisa_inicioNegociacao;
    }



    public boolean isAvisa_NegociacaoPendente() {
        return avisa_NegociacaoPendente;
    }



    public void setAvisa_NegociacaoPendente(boolean avisa_NegociacaoPendente) {
        this.avisa_NegociacaoPendente = avisa_NegociacaoPendente;
    }



    public boolean isAvisa_PropostaCancelamento() {
        return avisa_PropostaCancelamento;
    }



    public void setAvisa_PropostaCancelamento(boolean avisa_PropostaCancelamento) {
        this.avisa_PropostaCancelamento = avisa_PropostaCancelamento;
    }



    public boolean isAvisa_Cancelamento() {
        return avisa_Cancelamento;
    }



    public void setAvisa_Cancelamento(boolean avisa_Cancelamento) {
        this.avisa_Cancelamento = avisa_Cancelamento;
    }



    public boolean isAvisa_ConclusaoVenda() {
        return avisa_ConclusaoVenda;
    }



    public void setAvisa_ConclusaoVenda(boolean avisa_ConclusaoVenda) {
        this.avisa_ConclusaoVenda = avisa_ConclusaoVenda;
    }



    @CreationTimestamp 
    private Timestamp createDate;

    @UpdateTimestamp 
    private Timestamp updateDate;


    
    public negociacaoModel() {
    }



    public int getIdVenda() {
        return idVenda;
    }



    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }



    public String getstatusNegociacao() {
        return statusNegociacao;
    }



    public void setstatusNegociacao(String statusNegociacao) {
        this.statusNegociacao = statusNegociacao;
    }



    public int getQtd_comprada() {
        return qtd_comprada;
    }



    public void setQtd_comprada(int qtd_comprada) {
        this.qtd_comprada = qtd_comprada;
    }



    public boolean isAprovacao_vendedor() {
        return aprovacao_vendedor;
    }



    public void setAprovacao_vendedor(boolean aprovacao_vendedor) {
        this.aprovacao_vendedor = aprovacao_vendedor;
    }



    public boolean isAprovacao_comprador() {
        return aprovacao_comprador;
    }



    public void setAprovacao_comprador(boolean aprovacao_comprador) {
        this.aprovacao_comprador = aprovacao_comprador;
    }



    public int getId() {
        return id;
    }



    public Timestamp getCreateDate() {
        return createDate;
    }



    public Timestamp getUpdateDate() {
        return updateDate;
    }



    public String getSubStatusNegociacao() {
        return subStatusNegociacao;
    }



    public void setSubStatusNegociacao(String subStatusNegociacao) {
        this.subStatusNegociacao = subStatusNegociacao;
    }



    
}
