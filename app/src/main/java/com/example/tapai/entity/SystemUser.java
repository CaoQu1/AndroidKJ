package com.example.tapai.entity;

public class SystemUser {
    private String userName;
    private String passWord;

    public  SystemUser()
    {

    }

    public SystemUser(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }
}
