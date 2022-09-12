package com.example.timviec.model;

public class User {
    private String uid, name, phoneNumber;

    public User() {
        this.uid = "";
        this.name = "";
        this.phoneNumber = "";
    }

    public User(String uid, String name, String phoneNumber, String username, String password) {
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public User(String uid, String name, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
