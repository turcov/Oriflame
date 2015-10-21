/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.domain;

import java.util.Date;

/**
 *
 * @author Serguei
 */
public class Message {

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", message=" + message + ", isReaded=" + isReaded + ", date=" + date + '}';
    }

    private int id;
    private String name;
    private String email;
    private String phone;
    private String message;
    private static Message instance;
    private boolean isReaded;
    private Date date;
    
    
    
    public static Message getInstance() {
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
        instance=this;
    }

    public boolean isIsReaded() {
        return isReaded;
    }

    public void setIsReaded(boolean isReaded) {
        this.isReaded = isReaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
