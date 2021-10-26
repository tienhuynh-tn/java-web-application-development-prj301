/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String DISPATCH_CONTROLLER = "DispatchServlet";

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
        
        String url = LOGIN_PAGE;
        
        try {
//            HttpSession session = request.getSession(false);
//            
//            if (session != null) {
//                String username = (String)session.getAttribute("USER");
//
//                System.out.println(username + " Log out Servlet");
//
//                Cookie cookies = new Cookie(username, "");
//                cookies.setMaxAge(0);
//                response.addCookie(cookies);
//
//                session.invalidate();
//                url = DISPATCH_CONTROLLER;
//            }
            HttpSession session = request.getSession(false);
            
            if (session == null) {
                return;
            }
            
            String username = (String)session.getAttribute("USER");
            
            System.out.println(username + " Log out Servlet");
            
            Cookie cookies = new Cookie(username, "");
            cookies.setMaxAge(0);
            response.addCookie(cookies);
            
            session.invalidate();
//            response.addHeader("Pragma", "no-cache");
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.setDateHeader("Expires", 0);
        } finally {
            response.sendRedirect(url);
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
