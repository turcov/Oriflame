/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.Property;
import java.util.Map;

/**
 *
 * @author Serguei
 */
public interface PropertyServiceIntf {
    void editProperty(Property property);
    
    Property findPropertyByKey(String key);
    Map<String,Property> findAllProperties();
}
