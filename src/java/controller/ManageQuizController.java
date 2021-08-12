/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Ultil.SessionUltil;
import dao.QuizDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuizModel;
import model.UserModel;
import paging.PageRequest;

/**
 *
 * @author NguyenManhCuong
 */
@WebServlet(name = "ManageQuizController", urlPatterns = {"/managequiz"})
public class ManageQuizController extends HttpServlet {

    QuizDAO quizDAO = new QuizDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserModel model = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
            if (model == null) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            if (model.getRoleID() != 1) {
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            }
            int index;
            if (!request.getParameterMap().containsKey("index")) {
                index = 1;
            } else {
                try {
                    index = Integer.parseInt(request.getParameter("index"));
                } catch (NumberFormatException e) {
                    index = 1;
                }
            }
            int maxItemOnPage = 4;
            int totalItem = quizDAO.countByCreatedBy(model.getUsername());
            PageRequest page = new PageRequest(index, maxItemOnPage);
            int totalPage = (int) Math.ceil((double) totalItem / maxItemOnPage);
            List<QuizModel> listModel = quizDAO.findByOffset(model.getUsername(), page);
            request.setAttribute("totalItems", totalItem);
            request.setAttribute("listQuestion", listModel);
            request.setAttribute("totalPage", totalPage);
            RequestDispatcher rq = request.getRequestDispatcher("/view/ManageQuiz.jsp");
            rq.forward(request, response);
        } catch (Exception e) {
        }
    }

}
