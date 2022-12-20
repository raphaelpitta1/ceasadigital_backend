package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class cadastraUsuarioForm {
    @NotBlank(message = "Nome é um campo obrigatório")
    @Size(min = 3,max = 50,message = "O nome precisar ser entre 3 a 50 caracteres")
    private String nome;

    private String sobrenome;
    @NotBlank(message = "Tipoe_pessoa é um campo obrigatório")
    @Size(max = 8,message = "Tipoe_pessoa precisa ter no máximo 8 caracteres")
    private String tipo_pessoa;
    @NotBlank(message = "Documento é um campo obrigatório")
    @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres")
    private String documento;
    @NotBlank(message = "Senha é um campo obrigatório")
    @Size(min = 8,max = 20,message = "A senha precisa ter entre 8 a 20 caracteres")
    private String senha;
    @NotBlank(message = "Telefone é um campo obrigatório")
    @Size(min = 11,max = 11,message = "O Telefone precisa ter 11 caracteres")
    private String telefone;
    @NotBlank(message = "Cep é um campo obrigatório")
    @Size(min = 8,max = 11,message = "O CEP precisa ter 11 caracteres")
    private String cep;
    @Size(min = 0,max = 50,message = "O campo latitude deve conter no máximo 50 caracteres")
    private String latitude;
    @Size(min = 0,max = 50,message = "O campo longitude deve conter no máximo 50 caracteres")
    private String longitude;
    public cadastraUsuarioForm(
            @NotBlank(message = "Nome é um campo obrigatório") @Size(min = 3, max = 50, message = "O nome precisar ser entre 3 a 50 caracteres") String nome,
            String sobrenome,
            @NotBlank(message = "Tipoe_pessoa é um campo obrigatório") @Size(max = 8, message = "Tipoe_pessoa precisa ter no máximo 8 caracteres") String tipo_pessoa,
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11, max = 15, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String documento,
            @NotBlank(message = "Senha é um campo obrigatório") @Size(min = 8, max = 20, message = "A senha precisa ter entre 8 a 20 caracteres") String senha,
            @NotBlank(message = "Telefone é um campo obrigatório") @Size(min = 11, max = 11, message = "O Telefone precisa ter 11 caracteres") String telefone,
            @NotBlank(message = "Cep é um campo obrigatório") @Size(min = 8, max = 11, message = "O CEP precisa ter 11 caracteres") String cep,
            @Size(min = 0,max = 50,message = "O campo latitude deve conter no máximo 50 caracteres") String latitude,
            @Size(min = 0,max = 50,message = "O campo longitude deve conter no máximo 50 caracteres") String longitude
            
            ) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipo_pessoa = tipo_pessoa;
        this.documento = documento;
        this.senha = senha;
        this.telefone = telefone;
        this.cep = cep;
        this.latitude=latitude;
        this.longitude=longitude;
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
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
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
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    
    
}
