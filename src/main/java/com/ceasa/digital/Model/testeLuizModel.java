package com.ceasa.digital.Model;

import java.security.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity(name="teste_luiz")
public class testeLuizModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private int id;

    @Column(length = 255)
    private String nome;

    @CreationTimestamp
    private Timestamp cata_criacao;

    public testeLuizModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Timestamp getCata_criacao() {
        return cata_criacao;
    }

    public void setCata_criacao(Timestamp cata_criacao) {
        this.cata_criacao = cata_criacao;
    }

    public int getId() {
        return id;
    }


    
    
}
