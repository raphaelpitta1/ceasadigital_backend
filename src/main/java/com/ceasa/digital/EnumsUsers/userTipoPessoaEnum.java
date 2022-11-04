package com.ceasa.digital.EnumsUsers;

public enum userTipoPessoaEnum {
    
   FISICA("FISICA"),
   JURIDICA("JURIDICA"),
   INVALIDA("INVALIDA");

   private String value;


   userTipoPessoaEnum(String value) {
      this.value = value;
     
  }

  public String getValue() {
      return value;
  }


  public String getValueString() {
     
      return value;
  }
}
