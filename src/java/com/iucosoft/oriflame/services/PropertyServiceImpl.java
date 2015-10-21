/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.Property;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serguei
 */
public class PropertyServiceImpl implements PropertyServiceIntf {

    Properties propsRU;
    Properties propsRO;
    Properties propsEN;
    private final String PACKAGE = "com/iucosoft/oriflame/translates/";
    private final String path = Thread.currentThread().getContextClassLoader().getResource(PACKAGE).getPath();
    private final String RU_FILE = "text_ru.properties";
    private final String RO_FILE = "text_ro.properties";
    private final String EN_FILE = "text_en.properties";
    private final String fullpathRU=path + RU_FILE;
    private final String fullpathRO=path + RO_FILE;
    private final String fullpathEN=path + EN_FILE;
    private static final Logger LOG = Logger.getLogger(PropertyServiceImpl.class.getName());

    private PropertyServiceImpl() {
        propsRU = new Properties();
        propsRO = new Properties();
        propsEN = new Properties();
    }

    private static PropertyServiceImpl instance;

    public static PropertyServiceImpl getInstance() {
        if (instance == null) {
            instance = new PropertyServiceImpl();
        }
        return instance;
    }

    @Override
    public void editProperty(Property property) {
        try {
            propsRU.load(new FileInputStream(new File(fullpathRU)));
            propsRO.load(new FileInputStream(new File(fullpathRO)));
            propsEN.load(new FileInputStream(new File(fullpathEN)));
            System.out.println(property.getKey());
            System.out.println(property.getPropertyRU());
            propsRU.setProperty(property.getKey(), property.getPropertyRU());
            propsRO.setProperty(property.getKey(), property.getPropertyRO());
            propsEN.setProperty(property.getKey(), property.getPropertyEN());
            propsRU.store(new FileOutputStream(new File(fullpathRU)), null);
            propsRO.store(new FileOutputStream(new File(fullpathRO)), null);
            propsEN.store(new FileOutputStream(new File(fullpathEN)), null);
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Property findPropertyByKey(String key) {
        Property property=new Property();
        try {
            propsRU.load(new FileInputStream(new File(fullpathRU)));
            propsRO.load(new FileInputStream(new File(fullpathRO)));
            propsEN.load(new FileInputStream(new File(fullpathEN)));
            property.setKey(key);
            property.setPropertyRU(propsRU.getProperty(key));
            property.setPropertyRO(propsRO.getProperty(key));
            property.setPropertyEN(propsEN.getProperty(key));
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
       return property;
    }

    @Override
    public Map<String, Property> findAllProperties() {
        Map<String, Property> propsMap = new TreeMap<>();
        try {
            propsRU.load(new FileInputStream(new File(fullpathRU)));
            propsRO.load(new FileInputStream(new File(fullpathRO)));
            propsEN.load(new FileInputStream(new File(fullpathEN)));
            Set<String> stringPropertyNames = propsRU.stringPropertyNames();
            Property property;
            for (String stringPropertyName : stringPropertyNames) {
                property = new Property(stringPropertyName,
                        propsRU.getProperty(stringPropertyName),
                        propsRO.getProperty(stringPropertyName),
                        propsEN.getProperty(stringPropertyName));
                propsMap.put(stringPropertyName, property);
            }
        } catch (FileNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return propsMap;
    }

    public static void main(String[] args) {
        PropertyServiceIntf propertyService = PropertyServiceImpl.getInstance();
        Map<String, Property> properties = propertyService.findAllProperties();
        Set<Map.Entry<String, Property>> entrySet = properties.entrySet();
        for (Map.Entry<String, Property> set : entrySet) {
            System.out.print(set.getKey());
            System.out.print(" ");
            System.out.print(set.getValue());
            System.out.println("");
        }
    }
}
