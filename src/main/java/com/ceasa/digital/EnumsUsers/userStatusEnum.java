package com.ceasa.digital.EnumsUsers;

public enum userStatusEnum {
    
   ATIVO("true"),
   DESATIVADO("false");

   
   private String value;


   userStatusEnum(String value) {
      this.value = value;
     
  }

  public String getValue() {
      return value;
  }


  public String getValueString() {
     
      return value;
  }
}
