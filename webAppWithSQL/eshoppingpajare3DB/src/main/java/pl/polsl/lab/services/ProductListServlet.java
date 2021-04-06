/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.lab.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.lab.model.Eshopping;
import pl.polsl.lab.model.Product;

/**
 *
 * @author palas
 */
public class ProductListServlet extends HttpServlet {
    
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
        
        switch(action){
            case "Back":
                response.sendRedirect(request.getContextPath()+"/category.html");
                break;
            case "Logout":
                if(eshopping==null){
                    response.sendError(0);
                }
                //eshopping = (Eshopping) request.getSession().getAttribute("eshopping");
                eshopping.getClientMap().get(eshopping.returnLoggedUser()).setLogged(Boolean.FALSE);
                request.getSession().setAttribute("user", null);
                request.getSession().setAttribute("eshopping", eshopping);
                response.sendRedirect(request.getContextPath()+"/index.html");
                break;
            case "AddToBasket":
                try{
                    //int productName = Integer.parseInt(request.getParameter("selectedproductname"));
                    String productName = request.getParameter("selectedproductname");
                    String category = request.getSession().getAttribute("category").toString();
                    
                    Product selected = eshopping.findProduct(productName, eshopping.getCategoryList(category));
                    
                    if(selected.getAmount()>0){
                        eshopping.addToBasket(eshopping.returnLoggedUser(), selected);
                    eshopping.findProduct(productName, eshopping.getCategoryList(category)).
                            setAmount(selected.getAmount()-1);
                    }
                    request.getSession().setAttribute("eshopping", eshopping);
                    response.sendRedirect(request.getContextPath()+"/productList.html");
                } catch(Exception e){
                    response.sendError(response.SC_BAD_REQUEST, "Couldn't find product");
                }
                break;
            case "Basket":
                request.getSession().setAttribute("eshopping", eshopping);
                /*RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher("/BasketServlet");
                dispatcher.forward(request, response);*/
                response.sendRedirect(request.getContextPath()+"/basket.html");
                break;
            case "getallproducts": 
                try {
                    String category = request.getSession().getAttribute("category").toString();
                    printProductList((ArrayList<Product>) eshopping.getCategoryList(category), out);
                } catch (Exception e) {
                    response.sendError(response.SC_INTERNAL_SERVER_ERROR, 
                            "Error while retrieving list of products");
                }
                break;
            default:
                response.sendError(response.SC_INTERNAL_SERVER_ERROR, 
                            "No action found");
        }
    }

        
        private void printProduct(Product product, PrintWriter target) {
        target.println("<tr>");
        
        target.println("<td>");
        target.println("<input type=\"radio\" name=\"selectedproductname\" value=\""+product.getName()+"\" />");
        target.println("</td>");
        
        target.println("<td>");
        target.println(product.getName());
        target.println("</td>");
        
        target.println("<td>");
        target.println(product.getPrice().toString());
        target.println("</td>");
        
        target.println("<td>");
        target.println(product.getAmount().toString());
        target.println("</td>");
        
        target.println("<td>");
        target.println(product.getCategory());
        target.println("</td>");
        
        target.println("<td>");
        target.println(product.getDescription());
        target.println("</td>");
        
        target.println("</tr>");
    }
    
    /**
     * Prints a list of jobs as a table with radio buttons for selection
     * @param jobList Joblist to print.
     * @param target Print target
     */
    private void printProductList(ArrayList<Product> productList, PrintWriter target) {
        for (Product product : productList) {
            printProduct(product, target);
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
    
