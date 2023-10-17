package com.capstoneproject;

public class ForeverHomeFinderUser {
    private String userName;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private String phone;
    private int age;
    private float lat;
    private float lon;
    //constructor for User
    public ForeverHomeFinderUser() {
        this.userName = "";
        this.password = "";
        this.email = "";
        this.fName = "";
        this.lName = "";
        this.phone = "";
        this.age = 0;
        this.lat = 0;
        this.lon = 0;
    }

    public ForeverHomeFinderUser(String userName, String password, String email, String fName, String lName, String phone, int age, float lat, float lon) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.age = age;
        this.lat = lat;
        this.lon = lon;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }
    public void setInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
        System.out.println("Info set: Username - " + this.userName + ", Password - " + this.password);
    }
}
