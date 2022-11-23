package com.ceasa.digital.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name="Produtos")
public class produtoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
    @NotBlank
    @Column(length = 100)
     private String nome;

     @CreationTimestamp 
     private Timestamp createDate;
 
     
     @UpdateTimestamp 
     private Timestamp updateDate;


    public produtoModel() {
    }



    public produtoModel(String nome) {
        this.nome = nome;
    }



    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public Timestamp getCreateDate() {
        return createDate;
    }



    public Timestamp getUpdateDate() {
        return updateDate;
    }
 
    

     
    
}
