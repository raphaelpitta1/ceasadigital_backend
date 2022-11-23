package com.ceasa.digital.Model;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity(name="Oferta")
public class ofertaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idProduto;
    private int idUnMedida;
    private int idVendedor;
    private int qtdDisponivel;
    private float pesoUnMedida;
    private float vlUnMedida;
    private float vlKG;
    private boolean status=true;
    
    @CreationTimestamp 
    private Timestamp createDate;
    @UpdateTimestamp 
    private Timestamp updateDate;
    public ofertaModel() {
    }

    public ofertaModel(int idProduto, int idUnMedida, int idVendedor, int qtdDisponivel, float pesoUnMedida,
            float vlUnMedida, float vlKG) {
        this.idProduto = idProduto;
        this.idUnMedida = idUnMedida;
        this.idVendedor = idVendedor;
        this.qtdDisponivel = qtdDisponivel;
        this.pesoUnMedida = pesoUnMedida;
        this.vlUnMedida = vlUnMedida;
        this.vlKG = vlKG;
        
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
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

    public float getPesoUnMedida() {
        return pesoUnMedida;
    }

    public void setPesoUnMedida(float pesoUnMedida) {
        this.pesoUnMedida = pesoUnMedida;
    }

    public float getvlUnMedida() {
        return vlUnMedida;
    }

    public void setvlUnMedida(float vlUnMedida) {
        this.vlUnMedida = vlUnMedida;
    }

    public float getVlKG() {
        return vlKG;
    }

    public void setVlKG(float vlUnMedida, float pesoUnMedida) {

        NumberFormat formatter = new DecimalFormat("0.00");

        this.vlKG = Float.parseFloat(formatter.format(vlUnMedida / pesoUnMedida));
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

}