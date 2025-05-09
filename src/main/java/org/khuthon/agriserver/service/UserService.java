package org.khuthon.agriserver.service;

import org.khuthon.agriserver.dao.LandDao;
import org.khuthon.agriserver.dao.UserDao;
import org.khuthon.agriserver.dto.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDao userDao;
    private LandDao landDao;

    public UserService(UserDao userDao, LandDao landDao) {
        this.userDao = userDao;
        this.landDao = landDao;
    }
    public int login(String loginId, String loginPw) {
        User user = userDao.getUserByLoginId(loginId);
        if(user == null || !loginPw.equals(user.getUserPw())) {
            return 0;
        }

        // userDao.updateLoginStatus(user.getId(), 1);


        return user.getId();
    }

    public int logout(String ip) {
        User user = userDao.getUserByIp(ip);

        // userDao.updateLoginStatus(ip, user.getId(), 0);
        return 1;
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