///*
// * © 2021 tienhuynh.lttn
// * All rights reserved!
// * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
// */
//package tienhlt.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import tienhlt.utils.MyApplicationConstant;
//
///**
// *
// * @author Huỳnh Lê Thủy Tiên <tien.huynhlt.tn@gmail.com>
// */
//@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
//public class DispatchServlet extends HttpServlet {
//    
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone(); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public DispatchServlet() {
//    }
//    
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        request.setCharacterEncoding("UTF-8");
//        
//        //which buttin did user clicked?
//        String button = request.getParameter("btAction");
//        
//        ServletContext context = this.getServletContext();
//        Properties properties = (Properties)context.getAttribute("SITE_MAP");
//        
//        String url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.LOGIN_PAGE);
//        try {
//            if (button == null) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.STARTUP_APP_CONTROLLER);
//            } 
//            
//            
//            else if (button.equals("Login")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.LOGIN_CONTROLLER);
//            } else if (button.equals("Logout")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.LOGOUT_CONTROLLER);
//            } else if (button.equals("Sign Up")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.SIGN_UP_CONTROLLER);
//            }
//            
//            
//            else if (button.equals("Search")){
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.SEARCH_FULLNAME_CONTROLLER);
//            } else if (button.equals("Delete")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.CONFIRM_DELETE_CONTROLLER);
//            } else if (button.equals("Confirm")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.DELETE_ACCOUNT_CONTROLLER);
//            } else if (button.equals("Update")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.UPDATE_ACCOUNT_CONTROLLER);
//            } 
//            
//            
//            else if (button.equals("Buy")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.BUY_BOOK_CONTROLLER);
//            } else if (button.equals("Add Book to Your Cart")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.ADD_BOOK_TO_CART_CONTROLLER);
//            } else if (button.equals("View Your Cart")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.VIEW_CART_PAGE);
//            }   else if (button.equals("Remove Selected Books")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.REMOVE_BOOK_FROM_CART);
//            } else if (button.equals("Check Out Selected Books")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.CONFIRM_CHECK_OUT_CONTROLLER);
//            } else if (button.equals("Check Out")) {
//                url = properties.getProperty(
//                        MyApplicationConstant.DispatchFeatures.CHECK_OUT_ORDER_CONTROLLER);
//            } //end of if button is click 
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//            out.close();
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
