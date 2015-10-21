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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "editnewscms", urlPatterns = {"/cms/editnewscms.html"})
public class editnewscms extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(editnewscms.class.getName());

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
        String idNews = request.getParameter("id");
        int id = Integer.parseInt(idNews);
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        NewsServiceIntf newsService = NewsServiceDaoImpl.getInstance(dataSource);
        List<News> listNews = newsService.readNewsbyId(id);
        request.setAttribute("listNews", listNews);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/cms/cmseditnews.jsp");
        rd.forward(request, response);
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
        String idNews = request.getParameter("id");
        int id = Integer.parseInt(idNews);
        String datetimeStr = request.getParameter("DATETIME");
        String locale = request.getParameter("LOCALE");
        Date date = new Date();
        if (!datetimeStr.equals("")) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm");
            try {
                date = dateFormat.parse(datetimeStr);
            } catch (ParseException ex) {
                Logger.getLogger(editnewscms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String title = request.getParameter("TITLE");
        String info = request.getParameter("INFO");
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        NewsServiceIntf newsService = NewsServiceDaoImpl.getInstance(dataSource);
        News news = new News();
        news.setId(id);
        news.setDate(date);
        news.setTitle(title);
        news.setInfo(info);
        news.setLocale(locale);
        newsService.editNews(news);
        RequestDispatcher rd = request.getRequestDispatcher("newscms.html");
        rd.forward(request, response);
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
