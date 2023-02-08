package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class atualizaUsuarioForm {

   
 
    private int id;

    @NotBlank(message = "Nome é um campo obrigatório")
    @Size(min = 3,max = 255,message = "O nome precisar ser entre 3 a 255 caracteres")
    private String nome;


    @NotBlank(message = "Telefone é um campo obrigatório")
    @Size(min = 11,max = 11,message = "O Telefone precisa ter 11 caracteres")
    private String telefone;
    
    @NotBlank(message = "Telefone é um campo obrigatório")
    @Size(min = 8,max = 20,message = "O Telefone precisa ter 11 caracteres")
    private String senha;

    public atualizaUsuarioForm() {
    }

    public atualizaUsuarioForm(
           int id,
            @NotBlank(message = "Nome é um campo obrigatório") @Size(min = 3, max = 255, message = "O nome precisar ser entre 3 a 255 caracteres") String nome,
            @NotBlank(message = "Telefone é um campo obrigatório") @Size(min = 11, max = 11, message = "O Telefone precisa ter 11 caracteres") String telefone,
            @NotBlank(message = "Telefone é um campo obrigatório") @Size(min = 8, max = 20, message = "O Telefone precisa ter 11 caracteres") String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    
    
    

}
