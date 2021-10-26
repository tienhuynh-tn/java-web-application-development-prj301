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
@WebServlet(name = "ConfirmCheckOutServlet", urlPatterns = {"/ConfirmCheckOutServlet"})
public class ConfirmCheckOutServlet extends HttpServlet {
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String CONFIRM_CHECK_OUT_PAGE = "confirmCheckOut.jsp";
    
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
        
        String url = VIEW_CART_PAGE;
        
        try {
            HttpSession session = request.getSession(false); 
            if (session != null) {
                CartObject cart = (CartObject)session.getAttribute("CART");
                if (cart != null) {
                    Map<ProductDTO, Integer> items = cart.getItems();
                    if (items != null) {
                        String[] selectedItem = request.getParameterValues("chkCheckOut");
                        if (selectedItem != null) {
                            Map<ProductDTO, Integer> list = cart.showCheckedItems(selectedItem);
                            for (ProductDTO dto : list.keySet()) {
                                System.out.println(dto.getSKU() + " ConfirmCheckOutServlet");
                            }
                            session.setAttribute("CHECK_OUT_ITEMS", list);
                            url = CONFIRM_CHECK_OUT_PAGE;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            log("ConfirmCheckOutServlet_SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ConfirmCheckOutServlet_Naming: " + ex.getMessage());
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
