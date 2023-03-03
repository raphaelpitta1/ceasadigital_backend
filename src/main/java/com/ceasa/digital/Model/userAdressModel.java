package com.ceasa.digital.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;

@Entity(name = "Enderecos")
public class userAdressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Range(min = 1)
    private int idUsuario;
    @NotBlank(message = "CEP é um campo obrigatório")
    @Column(length = 15)
    private String cep;
    @Column(length = 255)
    private String bairro;
    @Column(length = 255)
    private String logradouro;
    @Column(length = 100)
    private int numero;
    @Column(length = 100)
    private String complemento;
    @Column(length = 50)
    private String cidade;
    @Column(length = 2)
    private String UF;
    @Column(length = 50)
    private String latitude;
    @Column(length = 50)
    private String longitude;
    private int radius;
    private Boolean status = true;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    public userAdressModel() {
    }

    public userAdressModel(@Range(min = 1) int idUsuario,
            @NotBlank(message = "CEP é um campo obrigatório") String cep,
            String bairro,
            String logradouro,
            int numero,
            String complemento,
            String cidade,
            String UF,
            String latitude,
            String longitude,
            int radius) {
        this.idUsuario = idUsuario;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.UF = UF;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String uF) {
        UF = uF;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

}
