package controller;

import Ultil.FormUltil;
import Ultil.MessageUltil;
import Ultil.SessionUltil;
import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserModel;

/**
 *
 * @author NguyenManhCuong
 */
@WebServlet(urlPatterns = {"/home", "/register", "/logout"})
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel userModel = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
        String url = request.getServletPath();
        String view = "/view/Home.jsp";
        if (url.equals("/register")) {
            view = "/view/Register.jsp";
        } else if (url.equals("/logout")) {
            SessionUltil.getInstance().removeValue(request, "USERMODEL");
            response.sendRedirect(request.getContextPath() + "/home?message=logout&alert=success"); // logout success
            return;
        } else { // login 
            if (userModel != null) { // login success => takequiz
                response.sendRedirect(request.getContextPath() + "/takequiz");
                return;
            }
        }
        MessageUltil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO o1 = new UserDAO();
        String url = request.getServletPath();
        // map data with object
        UserModel userModel = FormUltil.getDataFormInput(request);
        // register
        if (url.equals("/register")) {
            String urlSend = "";
            if (userModel.getUsername().trim().isEmpty() || Character.isDigit(userModel.getUsername().charAt(0))) {
                urlSend = request.getContextPath() + "/register?message=register_fail&alert=danger";
            } else if (userModel.getPassword().trim().isEmpty() || userModel.getPassword().length() < 6) {
                urlSend = request.getContextPath() + "/register?message=register_fail&alert=danger";
            } else if (userModel.getRoleID() == null) {
                urlSend = request.getContextPath() + "/register?message=register_fail&alert=danger";
            } else if (userModel.getEmail() == null) {
                urlSend = request.getContextPath() + "/register?message=register_fail&alert=danger";
            } else {
                try {
                    // check duplicate username
                    UserModel model = o1.findByUserName(userModel.getUsername());
                    if (model == null) {
                        o1.insertModel(userModel);
                        UserModel modelInsert = o1.findByUserNameAndPassword(userModel.getUsername(), userModel.getPassword());
                        if (modelInsert != null) {
                            urlSend = request.getContextPath() + "/register?message=register_sucess&alert=success";
                        } else {
                            urlSend = request.getContextPath() + "/register?message=register_fail&alert=danger";
                        }
                    } else {
                        urlSend = request.getContextPath() + "/register?message=username_password_exist&alert=danger";
                    }
                } catch (Exception ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
             response.sendRedirect(urlSend);
        } else { // login
            String urlSend = "";
            if (userModel.getUsername().trim().isEmpty() || Character.isDigit(userModel.getUsername().charAt(0))) {
                urlSend = request.getContextPath() + "/home?message=login_fail&alert=danger";
            } else if (userModel.getPassword().trim().isEmpty() || userModel.getPassword().length() < 6) {
                urlSend = request.getContextPath() + "/home?message=login_fail&alert=danger";
            } else {
                try {
                    UserModel model = o1.findByUserNameAndPassword(userModel.getUsername(), userModel.getPassword());
                    if (model != null) {
                        // login successfull => push on session
                        SessionUltil.getInstance().pushValue(request, "USERMODEL", model);
                        urlSend = request.getContextPath() + "/home?message=login_sucess&alert=success";
                    } else {
                        urlSend = request.getContextPath() + "/home?message=username_password_not_exist&alert=danger";
                    }
                } catch (Exception ex) {
                    Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect(urlSend);
        }
    }
}
