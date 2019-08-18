package com.zyzx.redbag.mapper;

import com.zyzx.redbag.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author zhy
 * @create 2019-08-18 14:38
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user")
    User checkUser(String tel);
    @Insert("insert into user(usertel) value(#{tel})")
    void insertUser(String tel);
}
