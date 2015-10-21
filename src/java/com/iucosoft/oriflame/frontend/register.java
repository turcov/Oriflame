package com.iucosoft.oriflame.frontend;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.iucosoft.oriflame.db.MyDataSource;
import com.iucosoft.oriflame.domain.ImagesPage;
import com.iucosoft.oriflame.domain.Message;
import com.iucosoft.oriflame.domain.Question;
import com.iucosoft.oriflame.domain.PageText;
import com.iucosoft.oriflame.services.ImagesPageServiceDaoImpl;
import com.iucosoft.oriflame.services.ImagesPageServiceIntf;
import com.iucosoft.oriflame.services.TextServiceDaoImpl;
import com.iucosoft.oriflame.services.TextServiceIntf;
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
 * @author Serguei
 */
@WebServlet(urlPatterns = {"/register.html"})
public class register extends HttpServlet {

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
        TextServiceIntf textService = TextServiceDaoImpl.getInstance(dataSource);
        String locale=(String)request.getSession().getAttribute("locale");
        PageText pageText = textService.findByPageName("registerpage",locale);
        request.setAttribute("text", pageText.getText());
        Question question=Question.getInstance();
        question.genQuestion();
        request.setAttribute("question", question.toString());
        new Message();
        ImagesPageServiceIntf imagesService=ImagesPageServiceDaoImpl.getInstance(dataSource);
        List<ImagesPage> imagesList=imagesService.findImagesByPageWithoutData(pageText);
        request.setAttribute("imagesList", imagesList);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/register.jsp");
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
