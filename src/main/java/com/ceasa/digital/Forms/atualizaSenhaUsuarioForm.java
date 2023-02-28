package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class atualizaSenhaUsuarioForm {

    @NotBlank(message = "Documento é um campo obrigatório")
    @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres")
    private String documento;
    @NotBlank(message = "Telefone é um campo obrigatório")
    @Size(min = 11,max = 11,message = "O telefone precisa ter 11 caracteres (XX) XXXXX-XXXX")
    private String telefone;
    public atualizaSenhaUsuarioForm(
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11, max = 15, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String documento,
            @NotBlank(message = "Telefone é um campo obrigatório") @Size(min = 11, max = 11, message = "O telefone precisa ter 11 caracteres (XX) XXXXX-XXXX") String telefone) {
        this.documento = documento;
        this.telefone = telefone;
    }
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String gettelefone() {
        return telefone;
    }
    public void settelefone(String telefone) {
        this.telefone = telefone;
    }    
   }
