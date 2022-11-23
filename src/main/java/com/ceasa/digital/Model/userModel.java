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

    @Column(length = 16)
    private String documento;
    @Column(length = 20)
    private String senha;
    @Column(length = 11)
    private String telefone;
 
    @Column(length = 8)
    private String cep;

    @Column(length = 50)
    private String latitude;

    @Column(length = 50)
    private String longitude;

    @Column(length = 10)
    private String tipo_usuario;

    private Boolean status = true;

    @CreationTimestamp 
    private Timestamp createDate;

    
    @UpdateTimestamp 
    private Timestamp updateDate;









    public userModel() {
    }


    


    // CADASTRO DE USUARIO

    public userModel(@NotBlank String nome, @NotBlank String sobrenome, String tipo_pessoa, @NotBlank String documento,
            @NotBlank String senha, @NotBlank String telefone, String tipo_usuario, String cep, String latitude, String longitude, Boolean status) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipo_pessoa = tipo_pessoa;
        this.documento = documento;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo_usuario = tipo_usuario;
        this.status = status;
        this.cep = cep;
        this.latitude=latitude;
        this.longitude=longitude;
    }




    // CADASTRO DE USUARIO COM SENHA

   




    // ATUALIZAÇÃO DE DADOS

    public userModel(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento,
            @NotBlank String telefone, String cep, String latitude, String longitude) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.telefone = telefone;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude = longitude;
    }








// atualização de status

    public userModel(@NotBlank String documento, Boolean status) {
        this.documento = documento;
        this.status = status;
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





    public String getCep() {
        return cep;
    }






    public void setCep(String cep) {
        this.cep = cep;
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





    public int getId() {
        return id;
    }





    public Timestamp getCreateDate() {
        return createDate;
    }





    public Timestamp getUpdateDate() {
        return updateDate;
    }

   

    
    
}


