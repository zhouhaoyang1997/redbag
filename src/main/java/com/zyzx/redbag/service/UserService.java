package com.zyzx.redbag.service;


/**
 * @author zhy
 * @create 2019-08-18 14:34
 **/
public interface UserService {
    public  boolean checkUser(String tel);

    void insertUser(String tel);
}
