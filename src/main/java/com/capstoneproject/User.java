package com.capstoneproject;

public class User {
    private String userName;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private String phone;
    private int age;
    private String address;
    //constructor for User
    public User() {
        this.userName = "";
        this.password = "";
        this.email = "";
        this.fName = "";
        this.lName = "";
        this.phone = "";
        this.age = 0;
        this.address = "";
    }

    public User(String userName, String password, String email, String fName, String lName, String phone, int age, String address) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String userName) {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInfo(String userName, String password){
        this.userName = userName;
        this.password = password;
        System.out.println("Info set: Username - " + this.userName + ", Password - " + this.password);
    }
}
