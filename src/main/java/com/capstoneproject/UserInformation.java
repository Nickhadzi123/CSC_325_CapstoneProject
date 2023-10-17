package com.capstoneproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

