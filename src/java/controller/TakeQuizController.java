
package controller;

import Ultil.FormUltil;
import Ultil.MessageUltil;
import Ultil.SessionUltil;
import dao.QuizDAO;
import java.io.IOException;
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
@WebServlet(name = "TakeQuizController", urlPatterns = {"/takequiz"})
public class TakeQuizController extends HttpServlet {

    QuizDAO quizDAO = new QuizDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel userModel = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
        String view = "";
        if (userModel != null) {
            QuizModel quizModel = (QuizModel) SessionUltil.getInstance().getValue(request, "QUIZMODEL");
            if (quizModel != null) {
                Integer id = (Integer) SessionUltil.getInstance().getValue(request, "IDQUIZRUN");
                // ------------------------------- 
                if (quizModel.getEndTime() < System.currentTimeMillis() || quizModel.getList().size() < id+1 ) {
                    //SessionUltil.getInstance().pushValue(request, "SCORE", getScore(quizModel.getList()));
                   // 3,343676
                   // 334,3567
                    double score = (double)Math.round(getScore(quizModel.getList())*100)/100 ;
                    request.setAttribute("SCORE", score);
                    SessionUltil.getInstance().removeValue(request, "QUIZMODEL");
                    SessionUltil.getInstance().removeValue(request, "IDQUIZRUN");
                    SessionUltil.getInstance().removeValue(request, "QUIZRUN");
                    SessionUltil.getInstance().removeValue(request, "SCORE");
                } else if (id != null && quizModel.getList().size() >= id + 1) {
                    QuizModel quizRunning = quizModel.getList().get(id);
                    request.setAttribute("QUIZRUN", quizRunning);
                }
            }
            view = "/view/TakeQuiz.jsp";
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        MessageUltil.showMessage(request);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserModel userModel = (UserModel) SessionUltil.getInstance().getValue(request, "USERMODEL");
        if (userModel != null) {
            QuizModel quiz = (QuizModel) SessionUltil.getInstance().getValue(request, "QUIZMODEL");
            if (request.getParameterMap().containsKey("number") && quiz == null) {
                    int numbers;
                    try {
                        // select number question
                        numbers = Integer.parseInt(request.getParameter("number"));
                        if (numbers <= 0) {
                            throw new Exception();
                        }
                        if (numbers > quizDAO.countAllQuiz()) {
                            response.sendRedirect(request.getContextPath() + "/takequiz?message=number_out_quiz&alert=danger");
                            return;
                        }
                    } catch (Exception e) {
                        response.sendRedirect(request.getContextPath() + "/takequiz?message=number_quiz_not_right&alert=danger");
                        return;
                    }
                    List<QuizModel> list = quizDAO.getListQuizRandom(numbers);
                    if (list != null) {
                        QuizModel quizModel = new QuizModel();
                        quizModel.setEndTime(System.currentTimeMillis() + numbers * 60000);
                        quizModel.setList(list);
                        SessionUltil.getInstance().pushValue(request, "QUIZMODEL", quizModel); // pust on enter number to get number of quiz
                        SessionUltil.getInstance().pushValue(request, "IDQUIZRUN", 0);
                    }
            } else if (request.getParameterMap().containsKey("nextQuiz")) {
                Integer id = (Integer) SessionUltil.getInstance().getValue(request, "IDQUIZRUN");
                QuizModel quizModel = (QuizModel) SessionUltil.getInstance().getValue(request, "QUIZMODEL");
                if (id != null) {
                    QuizModel quizRunning = quizModel.getList().get(id);
                    if (quizRunning != null) {
                        if (!FormUltil.checkData(request, "answers")) {
                            response.sendRedirect(request.getContextPath() + "/takequiz?message=error_answers&alert=danger");
                            return;
                        }
                        String[] answers = request.getParameterValues("answers");
                        System.out.println(request.getParameter("answers"));
                        if (answers.length > 4 || answers.length <= 0) {
                            response.sendRedirect(request.getContextPath() + "/takequiz?message=error_answers&alert=danger");
                            return;
                        }
                        if (!FormUltil.isRightOptions(answers)) {
                            response.sendRedirect(request.getContextPath() + "/takequiz?message=error_answers_f12&alert=danger");
                            return;
                        }
                        String[] answerRight = quizModel.getList().get(id).getAnswers();
                        int rightExpect = 0;
                        double rate = (double) 10 / quizModel.getList().size();// diem cua 1 cau dung
                        for (int i = 0; i < answerRight.length; i++) {
                            for (int j = 0; j < answers.length; j++) {
                                if (answerRight[i].trim().equalsIgnoreCase(answers[j].trim())) {
                                    rightExpect = rightExpect + 1;
                                }
                            }
                        }
                        double score = 0;
                        if (rightExpect == answers.length) {
                            score = rate;
                        } else {
                            score = (rate / 4) * rightExpect;
                        }
                        quizModel.getList().get(id).setScore(score);
                    }
                    SessionUltil.getInstance().pushValue(request, "IDQUIZRUN", (id + 1));
                }
            }
            response.sendRedirect(request.getContextPath() + "/takequiz");
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    public double getScore(List<QuizModel> list) {
        double score = 0;
        for (QuizModel quizModel : list) {
            score += quizModel.getScore();
        }
        return score;
    }
}
