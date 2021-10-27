/*
 * © 2021 tienhuynh.lttn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tienhlt.registration.RegistrationCreateError;
import tienhlt.registration.RegistrationDAO;
import tienhlt.registration.RegistrationDTO;

/**
 *
 * @author Huynh Le Thuy Tien
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
    private final String ERROR_PAGE = "signUp.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String firstname = request.getParameter("txtFirstname");
        String middlename = request.getParameter("txtMiddlename");
        String lastname = request.getParameter("txtLastname");
        boolean foundErr = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        
        System.out.println(firstname + " First");
        System.out.println(lastname + " Last");
        
        try {
            //1. Check user constraint validation
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUsernameLengthViolent("Username requires from 6 to 30 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthViolent("Password requires from 6 to 20 chars");
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password!!!");
            }
            if (firstname.trim().length() < 2 || firstname.trim().length() > 20) {
                foundErr = true;
                errors.setFirstNameLengthViolent("First name requires from 2 to 20 chars");
            }
            if (middlename.trim().length() < 0 || middlename.trim().length() > 20) {
                foundErr = true;
                errors.setMiddleNameLengthViolent("Middle name requires from 0 to 20 chars");
            }
            if (lastname.trim().length() < 2 || lastname.trim().length() > 20) {
                foundErr = true;
                errors.setLastNameLengthViolent("Last name requires from 2 to 20 chars");
            }
            //1.1 If so, notify to user correct them
            if (foundErr) {
                request.setAttribute("SIGNUPERRS", errors);
                return;
            } //en if Errors occur
            //1.2 If no error occurs, call DAO to insert to DB
            RegistrationDAO dao = new RegistrationDAO();
            username = username.trim();
            password = password.trim();
            RegistrationDTO dto = 
                    new RegistrationDTO(username, password, firstname, middlename, lastname, false);
            boolean result = dao.createNewAcccount(dto);
            //2. If task is success, redirect to Login Page
            if (result) {
                url = LOGIN_PAGE;
            } //end if insert is success
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SignUpServlet_SQL: " + ex.getMessage());
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("SIGNUPERRS", errors);
            }//end process username is existed in DB
        } catch (NamingException ex) {
            log("SignUpServlet_Naming: " + ex.getMessage());
        }
        finally {
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
