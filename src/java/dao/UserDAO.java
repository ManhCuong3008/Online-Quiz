package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import model.UserModel;

/**
 *
 * @author NguyenManhCuong
 */
public class UserDAO {

    // Khai báo các thành phần xử lý DB
    Connection cnn;// Kết nối CSDL
    PreparedStatement ps; // Thực hiện câu lệnh SQL
    ResultSet rs; // Lưu trữ và xử lý dư liệu
    DBContext db = new DBContext();

    public UserModel findByUserName(String username) throws Exception {
        try {
            String sqlQuery = "Select * from [User] where username = '" + username + "'";
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            UserModel userModel = null;
            while (rs.next()) {
//                userModel = new UserModel(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getInt(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getTimestamp(7));
                userModel = new  UserModel();
            }
            return userModel;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }

    public UserModel findByUserNameAndPassword(String username, String password) throws Exception {
        try {
            String sqlQuery = "Select * from [User] where username = ? and password = ?";
            cnn = new DBContext().getConnection();
            ps = cnn.prepareStatement(sqlQuery);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            UserModel userModel = null;
            while (rs.next()) {
                userModel = new UserModel(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getTimestamp(7));
            }
            return userModel;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }

    public void insertModel(UserModel userModel) throws Exception {
        try {
            String sqlQuery = "INSERT INTO dbo.[User]( username ,password ,roleid ,email ,created_by ,created_date)VALUES(?,?,?,?,?,?)";
            cnn = new DBContext().getConnection();
            Timestamp time = new Timestamp(System.currentTimeMillis());
            userModel.setCreateDate(time);
            userModel.setCreateBy("");
            ps = cnn.prepareStatement(sqlQuery);
            ps.setString(1, userModel.getUsername());
            ps.setString(2, userModel.getPassword());
            ps.setInt(3, userModel.getRoleID());
            ps.setString(4, userModel.getEmail());
            ps.setString(5, userModel.getCreateBy());
            ps.setTimestamp(6, time);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, cnn);
        }
    }

//    public static void main(String[] args) throws Exception {
//        UserDAO o1 = new UserDAO();
////        UserModel userModel = o1.findByUserNameAndPassword("cuongnm", "123456");
//        UserModel userModel = new UserModel();
//        userModel.setUsername("abc");
//        userModel.setPassword("12345678");
//        userModel.setRoleID(1);
//        userModel.setEmail("abc@gmail.com");
//        userModel.setCreateBy("aaaaa");
//        o1.insertModel(userModel);
//        System.out.println(userModel.getUsername());
//    }
}
