package org.khuthon.agriserver.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.User;

import java.util.List;

@Mapper
public interface UserDao {

    public boolean isLogined(String ip);
    public void join(String loginId, String loginPw, String nickname);
    public User getUserByLoginId(String loginId);
    // public void updateLoginStatus(String ip, int id, int i);
    public User getUserByIp(String ip);

    // 사용자 ID로 사용자 정보 조회
    User getUserById(int userId);
}
