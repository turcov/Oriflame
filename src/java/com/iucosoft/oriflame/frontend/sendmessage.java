/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.oriflame.frontend;

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.Message;
import com.iucosoft.oriflame.domain.Question;
import com.iucosoft.oriflame.services.MessagesServiceDaoImpl;
import com.iucosoft.oriflame.services.MessagesServiceIntf;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "sendmessage", urlPatterns = {"/sendmessage.html"})
public class sendmessage extends HttpServlet {

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
        RequestDispatcher rd;
        String name = request.getParameter("NAME");
        String email = request.getParameter("EMAIL");
        String phone = request.getParameter("PHONE");
        String message = request.getParameter("MESSAGE");
        String answer = request.getParameter("ANSWER");
        Message msgInstance = Message.getInstance();
        msgInstance.setName(name);
        msgInstance.setEmail(email);
        msgInstance.setPhone(phone);
        msgInstance.setMessage(message);
        int answerInt = -1;
        if (name.equals("")) {
            request.setAttribute("msgInstance", msgInstance);
            rd = request.getRequestDispatcher("register.html");
        } else if (!answer.equals("")) {
            answerInt = Integer.parseInt(answer);
        }
        if (Question.getInstance().getAnswer() != answerInt) {
            request.setAttribute("msgInstance", msgInstance);
            request.setAttribute("warning","style=\"border-color: red\"");
            rd = request.getRequestDispatcher("register.html");
        } else {
            request.removeAttribute("warning");
            MyDataSource dataSource = (MyDataSource) request.getServletContext().getAttribute("dataSource");
            MessagesServiceIntf messagesService=MessagesServiceDaoImpl.getInstance(dataSource);
            messagesService.addMessage(msgInstance);
            rd = request.getRequestDispatcher("/WEB-INF/pages/congrajulations.jsp");
        }
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
