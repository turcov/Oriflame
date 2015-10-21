/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.listeners;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.ImagesPage;
import com.iucosoft.oriflame.services.ImagesPageServiceDaoImpl;
import com.iucosoft.oriflame.services.ImagesPageServiceIntf;
import com.iucosoft.oriflame.utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Serguei
 */
public class ContextServletListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextServletListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyDataSource dataSource = MyDataSource.getInstance();
        sce.getServletContext().setAttribute("dataSource", dataSource);
        ImagesPageServiceIntf imageService=ImagesPageServiceDaoImpl.getInstance(dataSource);
        List<ImagesPage> imagesList=imageService.findAllWithoutData();
        String filename;
        String directory;
        String fullpath;
        File file;
        for (ImagesPage image : imagesList) {
            filename=image.getFilename();
            directory=image.getDirectory();
            fullpath=sce.getServletContext().getRealPath("/")+File.separator+directory+File.separator+filename;
            file=new File(fullpath);
            if(!file.exists()){
                image=imageService.finByIdWithData(image.getId());
                Utils.saveImageToFile(fullpath, new ByteArrayInputStream(image.getImageData()));
            }
        }
        String language=sce.getServletContext().getInitParameter("javax.servlet.jsp.jstl.fmt.locale");
        sce.getServletContext().setAttribute("language", language);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ((MyDataSource) sce.getServletContext().getAttribute("dataSource")).closeConnection();
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
         sce.getServletContext().removeAttribute("dataSource");
    }
}
