
package model;

import java.sql.Timestamp;
import java.util.List;

public class UserModel extends AbstractModel{
    public String username ;
    private  String password;
    private  Integer roleID;
    private  RoleModel roleModel ;
    private List<QuizModel> quiz;
    private String email;

    
    public UserModel() {
    }

    public UserModel(int id, String username, String password, Integer roleID, String email,String createBy,Timestamp createDate) {
        super(id, createDate, createBy);
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public List<QuizModel> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<QuizModel> quiz) {
        this.quiz = quiz;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
