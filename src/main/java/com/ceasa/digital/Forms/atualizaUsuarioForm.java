package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class atualizaUsuarioForm {
    @NotBlank(message = "Nome é um campo obrigatório")
    @Size(min = 3,max = 50,message = "O nome precisar ser entre 3 a 50 caracteres")
    private String nome;
    @NotBlank(message = "Sobrenome é um campo obrigatório")
    @Size(min = 3,max = 100,message = "O nome precisar ser entre 3 a 100 caracteres")
    private String sobrenome;
    @NotBlank(message = "Documento é um campo obrigatório")
    @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres")
    private String documento;
    @NotBlank(message = "Telefone é um campo obrigatório")
    @Size(min = 11,max = 11,message = "O Telefone precisa ter 11 caracteres")
    private String telefone;
    
    @Size(min = 8,max = 11,message = "O CEP precisa ter 11 caracteres")
    private String cep;
    private String latitude; 
    private String longitude;
    
    public atualizaUsuarioForm(
            @NotBlank(message = "Nome é um campo obrigatório") @Size(min = 3, max = 50, message = "O nome precisar ser entre 3 a 50 caracteres") String nome,
            @NotBlank(message = "Sobrenome é um campo obrigatório") @Size(min = 3, max = 100, message = "O nome precisar ser entre 3 a 100 caracteres") String sobrenome,
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String documento,
            @NotBlank(message = "Telefone é um campo obrigatório")  @Size(min = 11, max = 11, message = "O Telefone precisa ter 11 caracteres") String telefone, String cep, String latitude, String longitude) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.documento = documento;
        this.cep = cep;
        this.latitude = latitude;
        this.longitude =longitude;
    }


    public String getDocumento() {
        return documento;
    }


    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public void setCep(String cep) {
        this.cep = cep;
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getCep() {
        return cep;
    }


    public String getLatitude() {
        return latitude;
    }


    public String getLongitude() {
        return longitude;
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




    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    

}
