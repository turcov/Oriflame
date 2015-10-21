/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.cms;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.News;
import com.iucosoft.oriflame.services.NewsServiceDaoImpl;
import com.iucosoft.oriflame.services.NewsServiceIntf;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Turkov S
 */
@WebServlet(name = "newscms", urlPatterns = {"/cms/newscms.html"})
public class newscms extends HttpServlet {

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
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        NewsServiceIntf newsService = NewsServiceDaoImpl.getInstance(dataSource);
        List<News> newsListRU = newsService.findAllTitlesNewsByLocale("ru");
        List<News> newsListEN = newsService.findAllTitlesNewsByLocale("en");
        List<News> newsListMD = newsService.findAllTitlesNewsByLocale("ro");
        request.setAttribute("newsListRU", newsListRU);
        request.setAttribute("newsListEN", newsListEN);
        request.setAttribute("newsListMD", newsListMD);
        request.setAttribute("size",newsListRU.size());
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/cms/cmsnews.jsp");
        rd.forward(request, response);

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
