package com.ceasa.digital.Enums;

public enum vendaNegociacaoStatusEnum {
    
   EM_ANDAMENTO("PROCESSO"),
   CONCLUIDO("CONCLUIDO"),
   CANCELADO("CANCELADO");

   
   private String value;


   vendaNegociacaoStatusEnum(String value) {
      this.value = value;
     
  }

  public String getValue() {
      return value;
  }


  public String getValueString() {
     
      return value;
  }
}
