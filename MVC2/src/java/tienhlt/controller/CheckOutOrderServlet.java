/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tienhlt.cart.CartObject;
import tienhlt.product.ProductDTO;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "CheckOutOrderServlet", urlPatterns = {"/CheckOutOrderServlet"})
public class CheckOutOrderServlet extends HttpServlet {
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String CHECK_OUT_SUCCESS_PAGE = "checkOutSuccess.jsp";

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
        
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAdress");
        String total = request.getParameter("txtTotal");
        String url = VIEW_CART_PAGE;
        
        try {
            //1. Cust goes to cart place
            HttpSession session = request.getSession(false);
            //2. Cust takes his/her cart
            if (session != null) {
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    //3. Cust gets all items
                    Map<ProductDTO, Integer> items 
                            = (Map<ProductDTO, Integer>)session.getAttribute("CHECK_OUT_ITEMS");
                    if (items != null) {
                        boolean result = cart.checkOutItemsOfCart(name, address, total, items);
                        if (result) {
                            for (ProductDTO dto : items.keySet()) {
                                cart.removeItemFromCart(dto.getSKU());
                                System.out.println(dto.getSKU() + " CheckOutOrderServlet");
                            }
                            url = CHECK_OUT_SUCCESS_PAGE;
//                            session.removeAttribute("CART");
                            session.removeAttribute("CHECK_OUT_ITEMS");
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckOutOrderServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutOrderServlet_Naming: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
