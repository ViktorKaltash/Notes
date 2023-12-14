package com.notes.entity;

public class User {
    private final int id;
    private String email;
    private String password;
    private String nickname;
    private String date;

    public User(int id) {
        this.id = id;
    }
    public void setAccountName(String account_name) {
        nickname = account_name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getNickname() {return nickname;}
    public int getId() {return id;}
    public String getDate() {return date;}
    public void setEmail(String email) {this.email = email;}
    public void setNickname(String nickname) {this.nickname = nickname;}
    public void  setPassword(String password) {this.password = password;}
    public void setDate(String date) {this.date = date;}

    @Override
    public String toString() {
        return ("Id: " + id + "\nUser: " + nickname + "\nEmail: " + email + "\nRegistration date: " + date);
    }
}