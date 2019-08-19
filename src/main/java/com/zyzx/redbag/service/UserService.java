package com.zyzx.redbag.service;

import com.zyzx.redbag.entry.User;
import com.zyzx.redbag.mapper.UserMapper;
import com.zyzx.redbag.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @create 2019-08-18 14:36
 **/
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User checkUser(String tel) {
        return userMapper.checkUser(tel);

    }

    public void insertUser(String tel) {
        userMapper.insertUser(tel);
    }
    public void updateUserIsPartake(int userId){
        userMapper.updateUserIsPartake(userId);
    }
    public User checkIsPartake(String tel) {
        return userMapper.checkIsPartake(tel);

    }
}
