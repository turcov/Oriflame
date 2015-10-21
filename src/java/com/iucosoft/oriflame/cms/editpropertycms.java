/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.cms;

import com.iucosoft.oriflame.domain.Property;
import com.iucosoft.oriflame.services.PropertyServiceImpl;
import com.iucosoft.oriflame.services.PropertyServiceIntf;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Serguei
 */
@WebServlet(name = "editpropertycms", urlPatterns = {"/cms/editpropertycms.html"})
public class editpropertycms extends HttpServlet {

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
       String key=request.getParameter("key");
       PropertyServiceIntf propertyService= PropertyServiceImpl.getInstance();
       Property property=propertyService.findPropertyByKey(key);
       request.setAttribute("property", property);
       request.getRequestDispatcher("/WEB-INF/pages/cms/cmseditproperty.jsp").forward(request, response);
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
       String key=request.getParameter("key");
       String propRU=request.getParameter("propRU");
       String propRO=request.getParameter("propRO");
       String propEN=request.getParameter("propEN");
       Property property=new Property(key,propRU,propRO,propEN);
       PropertyServiceIntf propertyService= PropertyServiceImpl.getInstance();
       propertyService.editProperty(property);
       Map<String,Property> propsMap=propertyService.findAllProperties();
       request.setAttribute("propsMap", propsMap);
       request.getRequestDispatcher("/WEB-INF/pages/cms/cmsproperties.jsp").forward(request, response);
        
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
