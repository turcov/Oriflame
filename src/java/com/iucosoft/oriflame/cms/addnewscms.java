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
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serguei
 */
@WebServlet(name = "addnewscms", urlPatterns = {"/cms/addnewscms.html"})
public class addnewscms extends HttpServlet {

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
        News news=new News();
        news.setDate(new Date());
        news.setTitle("New Title");
        news.setInfo("Text from news");
        news.setLocale("en_US");
        news.setId(newsService.addNews(news));
        news.setTitle("Titlu nou");
        news.setInfo("Text din noutati");
        news.setLocale("ro_MD");
        newsService.addNews(news);
        news.setTitle("Новый заголовок");
        news.setInfo("Новостной текст");
        news.setLocale("ru_RU");
        newsService.addNews(news);
        List<News> listNews = newsService.readNewsbyId(news.getId());
        request.setAttribute("listNews", listNews);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/cms/cmseditnews.jsp");
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
