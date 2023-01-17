package com.ceasa.digital.Forms;

import javax.validation.constraints.NotBlank;

public class chatFirebaseUpdateuserChatForm {
    
    private int id;

    private String name;

    private String photo;

    private String status;


    public chatFirebaseUpdateuserChatForm() {
    }


    

    public chatFirebaseUpdateuserChatForm(int id, String name, String photo, String status) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.status = status;
    }




    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }




  

   
    

}
