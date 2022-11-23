package com.ceasa.digital.Enums;

public enum userStatusEnum {
    
   TRUE("true"),
   FALSE("false");

   
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
