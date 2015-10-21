/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.domain;

/**
 *
 * @author Serguei
 */
public class Property {
    private String key;
    private String propertyRU;
    private String propertyRO;
    private String propertyEN;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPropertyRU() {
        return propertyRU;
    }

    public void setPropertyRU(String propertyRU) {
        this.propertyRU = propertyRU;
    }

    public String getPropertyRO() {
        return propertyRO;
    }

    public void setPropertyRO(String propertyRO) {
        this.propertyRO = propertyRO;
    }

    public String getPropertyEN() {
        return propertyEN;
    }

    public void setPropertyEN(String propertyEN) {
        this.propertyEN = propertyEN;
    }

    public Property() {
    }

    public Property(String key, String propertyRU, String propertyRO, String propertyEN) {
        this.key = key;
        this.propertyRU = propertyRU;
        this.propertyRO = propertyRO;
        this.propertyEN = propertyEN;
    }

    @Override
    public String toString() {
        return "Property{" + "propertyRU=" + propertyRU + ", propertyRO=" + propertyRO + ", propertyEN=" + propertyEN + '}';
    }
    
    
}
