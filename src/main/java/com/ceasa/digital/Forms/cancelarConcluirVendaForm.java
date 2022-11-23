package com.ceasa.digital.Forms;


public class cancelarConcluirVendaForm {
 
    private int idVenda;
    private int idUsuario;



    
    public cancelarConcluirVendaForm(int idVenda, int idUsuario) {
        this.idVenda = idVenda;
        this.idUsuario = idUsuario;
    }
    public int getIdVenda() {
        return idVenda;
    }
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    } 
  

    
   }
