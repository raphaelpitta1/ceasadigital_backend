package com.ceasa.digital.Model;



public class chatFirebaseUserModel {

    private int id;
    private String name;
    private String photo;
    private String status;
    private String chatId;
    private String lastMessage;
    private String lastMessageTimeStamp;


    
    public chatFirebaseUserModel() {
    }



    public chatFirebaseUserModel(int id, String name, String photo, String status, String chatId, String lastMessage,
            String lastMessageString) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.status = status;
        this.chatId = chatId;
        this.lastMessage = lastMessage;
        this.lastMessageTimeStamp = lastMessageString;
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



    public String getChatId() {
        return chatId;
    }



    public void setChatId(String chatId) {
        this.chatId = chatId;
    }



    public String getLastMessage() {
        return lastMessage;
    }



    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }



    public String getlastMessageTimeStamp() {
        return lastMessageTimeStamp;
    }



    public void setlastMessageTimeStamp(String lastMessageTimeStamp) {
        this.lastMessageTimeStamp = lastMessageTimeStamp;
    }



}
