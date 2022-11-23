package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class unMedidaForm {

    
    @NotBlank(message = "Nome é um campo obrigatório para cadastrar uma nova unidae de medida")
    @Size(min = 3,max = 50,message = "O Nome precisa ter entre 3 e 50 caracteres")
    private String nome;

    public unMedidaForm(
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11, max = 15, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String nome) {
        this.nome = nome;
    }

    public unMedidaForm(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
   }
