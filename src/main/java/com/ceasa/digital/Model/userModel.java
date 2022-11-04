package com.ceasa.digital.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;




@Entity(name="Usuario")
public class userModel {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String nome;
    @Column(length = 100)
    private String sobrenome;
    @Column(length = 8)
    private String tipo_pessoa;
    @NotBlank
    @Column(length = 16)
    private String documento;
    @Column(length = 20)
    private String senha;
    @Column(length = 11)
    private String telefone;
    @Column(length = 10)
    private String tipo_usuario;
    private Boolean status;











    public userModel() {
    }


    



    public userModel(@NotBlank String nome, @NotBlank String sobrenome, String tipo_pessoa, @NotBlank String documento,
            @NotBlank String senha, @NotBlank String telefone, String tipo_usuario, Boolean status) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipo_pessoa = tipo_pessoa;
        this.documento = documento;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo_usuario = tipo_usuario;
        this.status = status;
    }






    public userModel(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento,
            @NotBlank String senha, @NotBlank String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.senha = senha;
        this.telefone = telefone;
    }






    public userModel(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento,
            @NotBlank String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.telefone = telefone;
    }






    public userModel(@NotBlank String documento, Boolean status) {
        this.documento = documento;
        this.status = status;
    }






    public void setId(int id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getSobrenome() {
        return sobrenome;
    }


    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }


    public String getTipo_pessoa() {
        return tipo_pessoa;
    }


    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }



    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public String getTipo_usuario() {
        return tipo_usuario;
    }


    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }


    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

   

    
    
}


