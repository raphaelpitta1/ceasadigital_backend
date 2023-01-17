package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class atualizaSenhaUsuarioForm {

    @NotBlank(message = "Documento é um campo obrigatório")
    @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres")
    private String documento;
    @NotBlank(message = "Senha é um campo obrigatório")
    @Size(min = 11,max = 11,message = "O telefone precisa ter 11 caracteres (xx)xxxxx-xxxx")
    private String telefone;
    public atualizaSenhaUsuarioForm(
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11, max = 15, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String documento,
            @NotBlank(message = "telefone é um campo obrigatório") @Size(min = 11, max = 11, message = "A senha precisa ter entre 8 a 20 caracteres") String senha) {
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
