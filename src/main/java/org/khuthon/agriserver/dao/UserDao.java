package org.khuthon.agriserver.dao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Mapper;
import org.khuthon.agriserver.dto.User;

import java.util.List;

@Mapper
public interface UserDao {

    
    public void join(String loginId, String loginPw, String nickname);
    public User getUserByLoginId(String loginId);
}
