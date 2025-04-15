package com.project.projectapi.dto;

import com.project.projectapi.enums.ActionType;

public class UserKafkaMessage {
    private Long Id;
    private String userName;
    private String email;
    private String phoneNumber;
    private ActionType actionType;

    public UserKafkaMessage() {
    }

    public UserKafkaMessage(Long Id,String userName, String email, String phoneNumber, ActionType actionType){
        this.Id = Id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.actionType = actionType;
    }

    public Long getId(){
        return Id;
    }

    public void setId(Long Id){
        this.Id = Id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public ActionType getActionType(){
        return actionType;
    }

    public void setActionType(ActionType actionType){
        this.actionType = actionType;

    }    
}
