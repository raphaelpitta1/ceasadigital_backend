package com.ceasa.digital.Enums;

public enum negociacaoSubStatusEnum {
    
   AGUARDANDO_APROVACAO_Vendedor_CANCELAMENTO("Aguardando vendedor aprovar cancelamento"),
   AGUARDANDO_APROVACAO_Comprador_CANCELAMENTO("Aguardando comprador aprovar cancelamento"),
   AGUARDANDO_APROVACAO_CONCLUSAO_Vendedor("Aguardando vendedor aprovar conclusão"),
   AGUARDANDO_APROVACAO_CONCLUSAO_Comprador("Aguardando comprador aprovar conclusão"),
   AGUARDANDO_APROVACAO_ALTERACAO("Aguardando aprovação da alteração de peso"),
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
