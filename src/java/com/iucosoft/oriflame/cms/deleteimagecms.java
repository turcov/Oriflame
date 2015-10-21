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
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serguei
 */
@WebServlet(name = "deleteimagecms", urlPatterns = {"/cms/deleteimagecms"})
public class deleteimagecms extends HttpServlet {

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
        MyDataSource myDataSource = (MyDataSource) getServletContext().getAttribute("myDataSource");
        ImagesPageServiceIntf imageService = ImagesPageServiceDaoImpl.getInstance(myDataSource);
        int imageId = Integer.parseInt(request.getParameter("imgId"));

        ImagesPage img = imageService.findByIdWithoutData(imageId);
        String directory = img.getDirectory();
        String filename = img.getFilename();

        Utils.deleteImage(getServletContext().getRealPath("/"), directory, filename);

        imageService.removeImage(img);

        TextServiceIntf textService = TextServiceDaoImpl.getInstance(myDataSource);
        String pagename = request.getParameter("pagename");
        List<PageText> textPages = textService.findByPageName(pagename);
        request.setAttribute("textPages", textPages);
        request.getRequestDispatcher("/WEB-INF/pages/cms/cmstext.jsp").forward(request, response);
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
        processRequest(request, response);
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