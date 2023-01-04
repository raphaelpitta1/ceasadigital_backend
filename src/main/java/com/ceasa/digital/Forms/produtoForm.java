package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class produtoForm {

    @NotBlank(message = "Nome é um campo obrigatório para cadastrar uma novo produto")
    @Size(min = 2, max = 50, message = "O Nome precisa ter entre 2 e 50 caracteres")
    private String nome;
    private String categoria;

    public produtoForm(
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 3, max = 50, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String nome,
            String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public produtoForm() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
