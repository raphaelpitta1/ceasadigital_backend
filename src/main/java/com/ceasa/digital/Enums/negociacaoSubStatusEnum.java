package com.ceasa.digital.Enums;

public enum negociacaoSubStatusEnum {
    
   AGUARDANDO_APROVACAO_Vendedor_CANCELAMENTO("Aguardando Vendedor Aprovar Cancelamento"),
   AGUARDANDO_APROVACAO_Comprador_CANCELAMENTO("Aguardando Comprador Aprovar Cancelamento"),
   AGUARDANDO_APROVACAO_CONCLUSAO_Vendedor("Aguardando Vendedor Aprovar Conclusao"),
   AGUARDANDO_APROVACAO_CONCLUSAO_Comprador("Aguardando Comprador Aprova Conclusao"),
   AGUARDANDO_APROVACAO_ALTERACAO("Aguardando Aprovacao Alteracao Peso"),
   CONCLUIDO("CONCLUIDO"),
   CANCELADO("CANCELADO");

   
   private String value;


   negociacaoSubStatusEnum(String value) {
      this.value = value;
     
  }

  public String getValue() {
      return value;
  }


  public String getValueString() {
     
      return value;
  }
}
