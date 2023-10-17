package com.capstoneproject;

public class UserInformation {
    private String username;
    private String password;

    public void setInfo(String username, String password){
        this.username = username;
        this.password = password;
        System.out.println("Info set: Username - " + this.username + ", Password - " + this.password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

