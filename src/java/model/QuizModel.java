package model;

import java.sql.Timestamp;
import java.util.List;

public class QuizModel extends AbstractModel {

    private String question;
    private List<String> option;
    private String[] answers;
    private double score = 0;
    private boolean status = false;
    private List<QuizModel> list;
    private long endTime;
    private int idUserCreated;

    public QuizModel() {
    }

    public QuizModel(int id ,String question, List<String> option, String[] answers, int idUserCreated,String createBy , Timestamp createDate ) {
        super(id, createDate, createBy);
        this.question = question;
        this.option = option;
        this.answers = answers;
        this.idUserCreated = idUserCreated;
    }

    
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<QuizModel> getList() {
        return list;
    }

    public void setList(List<QuizModel> list) {
        this.list = list;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getIdUserCreated() {
        return idUserCreated;
    }

    public void setIdUserCreated(int idUserCreated) {
        this.idUserCreated = idUserCreated;
    }
    
    
}
