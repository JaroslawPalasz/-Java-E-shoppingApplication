/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.lab.model.Eshopping;

/**
 *
 * @author palas
 */
public class CategoryServlet extends HttpServlet {

    private Eshopping eshopping;
    
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
        response.setContentType("text/html;charset=UTF-8");
        
        eshopping = (Eshopping) request.getSession().getAttribute("eshopping");
        
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        
        out.println(action + " ddd");
        
        
        switch(action){
            case "Logout":
            case "Back":
                if(eshopping==null){
                    response.sendError(0);
                }
                //eshopping = (Eshopping) request.getSession().getAttribute("eshopping");
                eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(Boolean.FALSE);
                request.getSession().setAttribute("user", null);
                request.getSession().setAttribute("eshopping", eshopping);
                response.sendRedirect(request.getContextPath()+"/index.html");
                break;
            case "Category1":
                request.getSession().setAttribute("category", action);
                response.sendRedirect(request.getContextPath()+"/productList.html");
                break;
            case "Category2":
                request.getSession().setAttribute("category", action);
                response.sendRedirect(request.getContextPath()+"/productList.html");
                break;
            case "Category3":
                request.getSession().setAttribute("category", action);
                response.sendRedirect(request.getContextPath()+"/productList.html");
                break;
            case "Category4":
                request.getSession().setAttribute("category", action);
                response.sendRedirect(request.getContextPath()+"/productList.html");
                break;
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
