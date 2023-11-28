package com.capstoneproject;

public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private int age;
    private String address;
    //constructor for User
    public User() {
        this.username = "";
        this.password = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.age = 0;
        this.address = "";
    }

    public User(String username, String password, String email, String firstName, String lastName, String phone, int age, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setInfo(String username, String password){
        this.username = username;
        this.password = password;
        System.out.println("Info set: Username - " + this.username + ", Password - " + this.password);
    }
}
