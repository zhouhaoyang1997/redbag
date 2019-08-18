package com.zyzx.redbag.serviceImpl;

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
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean checkUser(String tel) {
        User user = userMapper.checkUser(tel);
        if(user==null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void insertUser(String tel) {
        userMapper.insertUser(tel);
    }
}
