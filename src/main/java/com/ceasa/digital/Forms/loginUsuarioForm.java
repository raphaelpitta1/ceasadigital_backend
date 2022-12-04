package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class loginUsuarioForm {


    @NotBlank(message = "Documento é um campo obrigatório")
    private String documento;
    @NotBlank(message = "Senha é um campo obrigatório")
    private String senha;

   

    



    public loginUsuarioForm(
            @NotBlank(message = "Documento é um campo obrigatório") String documento,
            @NotBlank(message = "Senha é um campo obrigatório") String senha) {
        this.documento = documento;
        this.senha = senha;
    }


    public String getDocumento() {
        return documento;
    }
  
    public String getSenha() {
        return senha;
    }
  


   
    
    
}
