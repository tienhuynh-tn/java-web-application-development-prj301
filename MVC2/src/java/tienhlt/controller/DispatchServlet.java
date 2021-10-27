/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

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
 * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
 */
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
    private final String STARTUP_APP_CONTROLLER = "StartupApplicationServlet";
    private final String ADD_BOOK_TO_CART_CONTROLLER = "AddBookToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String LOGOUT_CONTROLLER = "LogoutServlet";
    private final String BUY_BOOK_CONTROLLER = "BuyBookServlet";
    private final String REMOVE_BOOK_FROM_CART = "RemoveBookFromCartServlet";
    private final String CHECK_OUT_ORDER_CONTROLLER = "CheckOutOrderServlet";
    private final String CONFIRM_CHECK_OUT_CONTROLLER = "ConfirmCheckOutServlet";
    private final String SIGN_UP_CONTROLLER = "SignUpServlet";
    private final String SEARCH_FULLNAME_CONTROLLER = "SearchFullNameServlet";

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public DispatchServlet() {
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //which buttin did user clicked?
        String button = request.getParameter("btAction");
        String url = LOGIN_PAGE;
        try {
            if (button == null) {
                //process cookies
                url = STARTUP_APP_CONTROLLER;
            } else if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")){
                url = SEARCH_FULLNAME_CONTROLLER;
            } else if (button.equals("Delete")) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } else if (button.equals("Add Book to Your Cart")) {
                url = ADD_BOOK_TO_CART_CONTROLLER;
            } else if (button.equals("View Your Cart")) {
                url = VIEW_CART_PAGE; 
            } else if (button.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (button.equals("Buy")) {
                url = BUY_BOOK_CONTROLLER;
            } else if (button.equals("Remove Selected Book")) {
                url = REMOVE_BOOK_FROM_CART;
            } else if (button.equals("Check Out")) {
                url = CHECK_OUT_ORDER_CONTROLLER;
            } else if (button.equals("Check Out Selected Book")) {
                url = CONFIRM_CHECK_OUT_CONTROLLER;
            } else if (button.equals("Sign Up")) {
                url = SIGN_UP_CONTROLLER;
            }//end of if button is click 
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
