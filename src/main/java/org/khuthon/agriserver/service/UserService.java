package org.khuthon.agriserver.service;

import org.khuthon.agriserver.dao.UserDao;
import org.khuthon.agriserver.dto.User;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    private UserDao userDao;
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public int login(String loginId, String loginPw) {
        User user = userDao.getUserByLoginId(loginId);
        if(user == null || !loginPw.equals(user.getUser_pw())) {
            return 0;
        }


        return user.getId();
    }

    public boolean join(String loginId, String loginPw, String nickname) {
        try {
            userDao.join(loginId, loginPw, nickname);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}