package com.example.user.entity;

import javax.persistence.Column;


public class Token {
    private String access_token;
    private String userID;
    private String userName;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Token(String access_token, String userID, String userName) {
        this.access_token = access_token;
        this.userID = userID;
        this.userName = userName;
    }

    public Token() {
    }
}
