package com.capstoneproject;

public class ForeverHomeFinderEmployee {
    private String employeeName;
    private String password;
    private String company;
    private int id;

    public ForeverHomeFinderEmployee() {
        this.employeeName = "";
        this.password = "";
        this.company = "";
        this.id = 0;
    }

    public ForeverHomeFinderEmployee(String employeeName, String password, String company, int id) {
        this.employeeName = employeeName;
        this.password = password;
        this.company = company;
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setUserName(String userName) {
        this.employeeName = userName;
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
