/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.domain;

/**
 *
 * @author iurasun
 */
public class PageText {
    private int id;
    private String pageName;
    private String text;
    private String locale;

    public PageText() {
    }

    public PageText(int id) {
        this.id = id;
    }
    
    public PageText(String pageName, String text,String locale) {
        this.pageName = pageName;
        this.text = text;
        this.locale=locale;
    }

    public PageText(int id, String pageName, String text,String locale) {
        this.id = id;
        this.pageName = pageName;
        this.text = text;
        this.locale=locale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "pageName=" + pageName + ", text=" + text+ ", locale=" + locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
    
}
