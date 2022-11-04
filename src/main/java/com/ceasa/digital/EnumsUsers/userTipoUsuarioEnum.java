package com.ceasa.digital.EnumsUsers;

public enum userTipoUsuarioEnum {
    
   Tipo_1("Hibrido"),
   Tipo_2("Comprador"),
   Tipo_3("Vendedor");

   private String value;


   userTipoUsuarioEnum(String value) {
      this.value = value;
     
  }

  public String getValue() {
      return value;
  }


  public String getValueString() {
     
      return value;
  }
}
