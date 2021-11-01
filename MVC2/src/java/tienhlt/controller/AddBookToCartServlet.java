/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.cart.CartObject;
import tienhlt.utils.MyApplicationConstant;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "AddBookToCartServlet", urlPatterns = {"/AddBookToCartServlet"})
public class AddBookToCartServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String url = MyApplicationConstant.AddBookToCartFeatures.ERROR_PAGE;
        
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession();
            //2. Cust takes their --> attributes
            CartObject cart = (CartObject)session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObject();
            }//cart is not existed --> create cart
            //3. Cust takes book item --> parameter
            String SKU = request.getParameter("pk");
            //4. Cust drops item down
            cart.addItemToCart(SKU);
            //5. Update to cart place
            session.setAttribute("CART", cart);
            //6. Cust goes to shopping
//            url = "DispatchServlet"
//                    + "?btAction=Buy";
            url = MyApplicationConstant.AddBookToCartFeatures.SHOW_BOOK_CONTROLLER;
        } catch (SQLException ex) {
            log("AddBookToCartServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("AddBookToCartServlet_Naming: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
            out.close();
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
