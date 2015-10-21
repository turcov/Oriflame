/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.frontend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serguei
 */
public class dispathcer extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(dispathcer.class.getName());

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

        String page;
        String path = request.getServletPath();
        request.setAttribute("Error", "Nu such address!!!");
        String active="class=\"active\"";
        switch (path) {
            case "/about.html":
                page = "about.html";
                request.setAttribute("active", active);
                break;
            case "/news.html":
                page = "news.html";
                request.setAttribute("active", active);
                break;
            case "/register.html":
                page = "register.html";
                request.setAttribute("active", active);
                break;
            case "/sendmessage.html":
                page = "sendmessage.html";
                break;
            case "/textcms.html":
                page = "/cms/textcms.html";
                break;
            case "/messagescms.html":
                page = "/cms/messagescms.html";
                break;
            case "/removemessagecms.html":
                page = "/cms/removemessagecms.html";
                break;
            case "/readmessagecms.html":
                page = "/cms/readmessagecms.html";
                break;
            case "/newscms.html":
                page = "/cms/newscms.html";
                break;
            case "/addnewscms.html":
                page = "/cms/addnewscms.html";
                break;
            case "/editnewscms.html":
                page = "/cms/editnewscms.html";
                break;
            case "/editpropertycms.html":
                page = "/cms/editpropertycms.html";
                break;
            case "/login.html":
                page = "login.html";
                break;
            case "/cms/logout.html":
                page = "/logout.html";
                break;
            default:
                page = "error.jsp";
                request.setAttribute("error", "Nu such address!!!");
        }
        LOG.info("attribute is "+active);
        RequestDispatcher rd = request.getRequestDispatcher(page);
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
