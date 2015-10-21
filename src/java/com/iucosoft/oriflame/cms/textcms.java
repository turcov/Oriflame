/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.cms;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.PageText;
import com.iucosoft.oriflame.services.TextServiceDaoImpl;
import com.iucosoft.oriflame.services.TextServiceIntf;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "aboutcms", urlPatterns = {"/cms/textcms.html"})
public class textcms extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(textcms.class.getName());

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
        String pagename = request.getParameter("pagename");
        request.getSession().setAttribute("pagename", pagename);
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        TextServiceIntf textService = TextServiceDaoImpl.getInstance(dataSource);
     //   TextServiceIntf textService = TextServiceMockImpl.getInstance();
        List<PageText> textPages = textService.findByPageName(pagename);
        request.setAttribute("textPages", textPages);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/cms/cmstext.jsp");
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
        String pagename = request.getParameter("pagename");
        String locale=request.getParameter("locale");
        String newtext = request.getParameter("pagetext");
        MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
        TextServiceIntf textService = TextServiceDaoImpl.getInstance(dataSource);
//        TextServiceIntf textService = TextServiceMockImpl.getInstance();
        PageText pageText = textService.findByPageName(pagename,locale);
        pageText.setText(newtext);
        textService.save(pageText);
//        request.setAttribute("message", "Saved succesfully");
        doGet(request, response);
//        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/cms/cmstext.jsp");
//        rd.forward(request, response);
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
