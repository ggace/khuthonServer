package org.khuthon.agriserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String userEmail;
    private String userPw;
    private List<Land> lands;

    public int getId() {
        return id;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPw() {
        return userPw;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserEmail(String user_email) {
        this.userEmail = user_email;
    }
    public void setUserName(String user_name) {
        this.userName = user_name;
    }
    public void setUserPw(String user_pw) {
        this.userPw = user_pw;
    }
}
