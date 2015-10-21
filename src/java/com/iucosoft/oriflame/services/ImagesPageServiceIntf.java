/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.services;

import com.iucosoft.oriflame.domain.ImagesPage;
import com.iucosoft.oriflame.domain.PageText;
import java.util.List;

/**
 *
 * @author Turkov S
 */
public interface ImagesPageServiceIntf {
    void addImage(ImagesPage img);
    void removeImage(ImagesPage img);
    
    ImagesPage findByIdWithoutData(int idImg);
    List<ImagesPage> findImagesByPageWithoutData(PageText page);
    int countImagesByPage(PageText page);
    List<ImagesPage> findAllWithoutData();

    ImagesPage finByIdWithData(int idImg);
}
