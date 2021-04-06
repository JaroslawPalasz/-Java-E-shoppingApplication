/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.lab.model.Eshopping;
import pl.polsl.lab.myexception.MyException;

/**
 *
 * @author palas
 */
public class LoginServlet extends HttpServlet {

    private Eshopping eshopping;
    
    @Override
    public void init(){
        try {
            eshopping = new Eshopping();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        //response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String key = null;
        
        try {
            //check if client exists, then login
            key = eshopping.checkAndReturnLogin(username, password);
        } catch (MyException ex) {
            out.print(key);
            //Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key!=null){
            //set eshopping attribute
        //Job job = model.getJobList().get(jobId);
        eshopping.getClientMap().get(key).setLogged(Boolean.TRUE);
        //request.getSession().setAttribute("user", eshopping.getClientMap().get(key));
        request.getSession().setAttribute("eshopping", eshopping);
        //request.getSession().setAttribute("job", job);
        //response.sendRedirect(request.getContextPath()+"/jobhistory.html");
        ////change servlet
        response.sendRedirect(request.getContextPath()+"/category.html");
        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CategoryServlet");
        //dispatcher.forward(request, response);
        //out.print(key);
        }
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
