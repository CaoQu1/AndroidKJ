package com.example.tapai.entity;

import java.util.Date;

public class QrCode {
    private String code;
    private Date date;

    public QrCode(){

    }

    public QrCode(String code, Date date) {
        this.code = code;
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
