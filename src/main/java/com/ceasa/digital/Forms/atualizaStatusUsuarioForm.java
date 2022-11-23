package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class atualizaStatusUsuarioForm {

    @NotBlank(message = "Documento é um campo obrigatório")
    @Size(min = 11,max = 15,message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres")
    private String documento;
    private boolean Status;
    
    
    
    public atualizaStatusUsuarioForm(
            @NotBlank(message = "Documento é um campo obrigatório") @Size(min = 11, max = 15, message = "O documento precisa ter entre 11(CPF) a 15(CNPJ) caracteres") String documento,
            @NotBlank(message = "Status é um campo obrigatório")  boolean status) {
        this.documento = documento;
        Status = status;
    }
    
    
    
    public String getDocumento() {
        return documento;
    }
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public boolean isStatus() {
        return Status;
    }
    public void setStatus(boolean status) {
        Status = status;
    }
    

    
   }
