/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.cms;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.ImagesPage;
import com.iucosoft.oriflame.domain.PageText;
import com.iucosoft.oriflame.services.ImagesPageServiceDaoImpl;
import com.iucosoft.oriflame.services.ImagesPageServiceIntf;
import com.iucosoft.oriflame.services.TextServiceDaoImpl;
import com.iucosoft.oriflame.services.TextServiceIntf;
import com.iucosoft.oriflame.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Turkov S
 */
@WebServlet(name = "imagescms", urlPatterns = {"/cms/imagescms.html"})
@MultipartConfig
public class imagescms extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String SEPARATOR = File.separator;
        String dir = "images";
        String path = getServletContext().getRealPath("/") + SEPARATOR + dir + SEPARATOR;
        Part filePart = request.getPart("file");
        RequestDispatcher rd = null;
        InputStream is = filePart.getInputStream();
        String pagename = request.getParameter("pagename");
        String locale = request.getParameter("locale");
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        TextServiceIntf textService = TextServiceDaoImpl.getInstance(dataSource);
        PageText pageText = textService.findByPageName(pagename, locale);

        ImagesPageServiceIntf imagesPageService = ImagesPageServiceDaoImpl.getInstance(dataSource);

        String filename = pagename + locale + (imagesPageService.countImagesByPage(pageText) + 1) + ".jpg";
        String fullpath = path + SEPARATOR + filename;

        Utils.saveImageToFile(fullpath, is);
        File f = new File(fullpath);

        ImagesPage imagesPage = new ImagesPage(Files.readAllBytes(f.toPath()),
                pageText.getId(), filename, dir);
        List<PageText> textPages = textService.findByPageName(pagename);
        request.setAttribute("textPages", textPages);
        imagesPageService.addImage(imagesPage);

        request.getRequestDispatcher("/WEB-INF/pages/cms/cmstext.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
