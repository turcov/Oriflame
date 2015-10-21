/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.News;
import java.util.List;

/**
 *
 * @author Serguei
 */
public interface NewsServiceIntf {
    int addNews(News news);
    void editNews(News news);
    void removeNews(int id);
    
    News readNewsbyIdLocale(int id,String locale);
    List<News> readNewsbyId(int id);
    List<News> findAllTitlesNewsByLocale(String locale);
    List<News> findAllNewsByLocale(String locale);
    List<String> findAllLocales();
}
