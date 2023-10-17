package com.capstoneproject;

public class ForeverHomeFinderEmployee {
    private String userName;
    private String password;
    private String company;
    private int id;

    public ForeverHomeFinderEmployee() {
        this.userName = "";
        this.password = "";
        this.company = "";
        this.id = 0;
    }

    public ForeverHomeFinderEmployee(String userName, String password, String company, int id) {
        this.userName = userName;
        this.password = password;
        this.company = company;
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
