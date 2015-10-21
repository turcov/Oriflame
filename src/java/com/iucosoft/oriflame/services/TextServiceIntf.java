/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.PageText;
import java.util.List;

/**
 *
 * @author iurasun
 */
public interface TextServiceIntf {
   

    void save(PageText pt);//update
    List<PageText> findByPageName(String pageName);//read
    PageText findByPageName(String pageName,String locale);//read
    
    List<String> findAllLocales();
}
