package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.QuizModel;
import paging.PageRequest;

/**
 *
 * @author NguyenManhCuong
 */
public class QuizDAO {

    // Khai báo các thành phần xử lý DB
    Connection cnn;// Kết nối CSDL
    PreparedStatement ps; // Thực hiện câu lệnh SQL
    ResultSet rs; // Lưu trữ và xử lý dư liệu
    DBContext db = new DBContext();
    
//    public QuizModel findByID(long id) throws Exception {
//        try {
//            String sqlQuery = "SELECT * FROM dbo.Question WHERE id ="+id+" ";
//            cnn = new DBContext().getConnection();
//            ps = cnn.prepareStatement(sqlQuery);
//            rs = ps.executeQuery();
//            QuizModel quizModel = null;
//            while (rs.next()) {
//                quizModel = new QuizModel();
//            }
//            return quizModel;
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            db.closeConnection(rs, ps, cnn);
//        }
//    }
  
    public void insertQuiz(QuizModel modelQuiz) throws Exception {
        try {
            String sqlQuery = "INSERT dbo.Question( question ,option1 ,option2 ,option3 ,option4 ,answers , user_id ,created_by ,created_date)"
                    + "VALUES  ( ? , ? , ? , ? , ? , ? , ? , ? ,? )";
            cnn = new DBContext().getConnection();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            modelQuiz.setCreateDate(time);
            String [] arrAnsers =  modelQuiz.getAnswers();
            String answers = "";
            for (int i = 0; i < arrAnsers.length; i++) {
                int lastIndex = arrAnsers.length -1 ;
                if (i!= lastIndex) {
                    answers = answers + arrAnsers[i]+",";
                }else{
                    answers = answers + arrAnsers[i];
                }
            }
            ps = cnn.prepareStatement(sqlQuery);
            ps.setString(1, modelQuiz.getQuestion());
            ps.setString(2, modelQuiz.getOption().get(0));
            ps.setString(3, modelQuiz.getOption().get(1));
            ps.setString(4, modelQuiz.getOption().get(2));
            ps.setString(5, modelQuiz.getOption().get(3));
            ps.setString(6, answers);
            ps.setInt(7, modelQuiz.getIdUserCreated());
            ps.setString(8, modelQuiz.getCreateBy());
            ps.setTimestamp(9, modelQuiz.getCreateDate());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }

    public int countAllQuiz() throws Exception {
        try {
            String sqlQuery = "Select COUNT(*) from [Question]";
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            int number = 0;
            while (rs.next()) {
                number = rs.getInt(1);
            }
            return number;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }

    }
 public List<QuizModel> getListQuizRandom(int number)   {
        try {
            String sqlQuery = "SELECT TOP " + number + " * FROM Question ORDER BY CHECKSUM(NEWID())";
            List<QuizModel> list = new ArrayList<QuizModel>();
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String question =rs.getString(2);
                List<String> listOption = new ArrayList<>();
                listOption.add(rs.getString(3));
                listOption.add(rs.getString(4));
                listOption.add(rs.getString(5));
                listOption.add(rs.getString(6));
                String []arrAnswer = rs.getString(7).split(",");
                int  idUser = rs.getInt(8);
                String userCreate =  rs.getString(9);
                Timestamp dateCreate = rs.getTimestamp(10);
                list.add(new QuizModel(id, question, listOption, arrAnswer, idUser, userCreate, dateCreate));
            }
            return list;
        } catch (Exception e) {
        } 
        return null;
    }
 
    public int countByCreatedBy(String username) throws SQLException, Exception {

        try {
            String sqlQuery = "Select COUNT(*) from [Question] where created_by = '"+username+"'";
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }
    
    public List<QuizModel> findByOffset(String username, PageRequest page) throws Exception {
        try {
            String sqlQuery = "Select * from [Question] where created_by = ? order by id desc offset ? rows fetch next ? rows only";
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setInt(2, page.getOffset());
            ps.setInt(3, page.getLimit());
            rs = ps.executeQuery();
            List<QuizModel> list = new ArrayList<QuizModel>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String question =rs.getString(2);
                List<String> listOption = new ArrayList<>();
                listOption.add(rs.getString(3));
                listOption.add(rs.getString(4));
                listOption.add(rs.getString(5));
                listOption.add(rs.getString(6));
                String []arrAnswer = rs.getString(7).split(",");
                int  idUser = rs.getInt(8);
                String userCreate =  rs.getString(9);
                Timestamp dateCreate = rs.getTimestamp(10);
                list.add(new QuizModel(id, question, listOption, arrAnswer, idUser, userCreate, dateCreate));
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }
//    public static void main(String[] args) {
//        QuizDAO o1 = new QuizDAO();
////        int number = o1.countAllQuiz();
////        int count = o1.countByCreatedBy("cuongnm");
////           List<QuizModel> list = o1.findByOffset("cuongnm", new paging.PageRequest());
//        List<QuizModel> list = o1.getListQuizRandom(3);
//        for (QuizModel item : list) {
//            System.out.println(item.getQuestion());
//        }
//       
//    }
}
