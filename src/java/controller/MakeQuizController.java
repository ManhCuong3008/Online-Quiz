/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Ultil.FormUltil;
import Ultil.MessageUltil;
import Ultil.SessionUltil;
import dao.QuizDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.QuizModel;
import model.UserModel;

/**
 *
 * @author NguyenManhCuong
 */
@WebServlet(name = "MakeQuizController", urlPatterns = {"/makequiz"})
public class MakeQuizController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
        if (model == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        if (model.getRoleID() != 1) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        MessageUltil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher("/view/MakeQuiz.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel model = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
        if (model == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        if (FormUltil.checkData(request, "question")) {
            try {
                String question = request.getParameter("question").trim();
                if (!FormUltil.checkData(request, "option1") || !FormUltil.checkData(request, "option2")
                        || !FormUltil.checkData(request, "option3") || !FormUltil.checkData(request, "option4")) {
                    response.sendRedirect(request.getContextPath() + "/makequiz?message=error_option&alert=danger");
                    return;
                }
                String option1 = request.getParameter("option1").trim();
                String option2 = request.getParameter("option2").trim();
                String option3 = request.getParameter("option3").trim();
                String option4 = request.getParameter("option4").trim();
                List<String> listOption = new ArrayList<>();
                listOption.add(option1);
                listOption.add(option2);
                listOption.add(option3);
                listOption.add(option4);
                if (!FormUltil.checkData(request, "answers")) { 
                    response.sendRedirect(request.getContextPath() + "/makequiz?message=error_answers&alert=danger");
                    return;
                }
                String[] answers = request.getParameterValues("answers");
                if (answers.length > 4 || answers.length <= 0) {
                    response.sendRedirect(request.getContextPath() + "/makequiz?message=error_answers&alert=danger");
                    return;
                }
                if (!FormUltil.isRightOptions(answers)) {
                    response.sendRedirect(request.getContextPath() + "/makequiz?message=error_answers_f12&alert=danger");
                    return;
                }
                QuizModel modelQuiz = new QuizModel();
                modelQuiz.setQuestion(question);
                modelQuiz.setOption(listOption);
                modelQuiz.setAnswers(answers);
                modelQuiz.setCreateBy(model.getUsername());
                modelQuiz.setIdUserCreated(model.getId());
                QuizDAO o1 = new QuizDAO();
                o1.insertQuiz(modelQuiz);
                response.sendRedirect(request.getContextPath() + "/makequiz?message=insert_question_done&alert=success");
            } catch (Exception ex) {
                Logger.getLogger(MakeQuizController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/makequiz?message=error_question&alert=danger");
        }
    }

}
