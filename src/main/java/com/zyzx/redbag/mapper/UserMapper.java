package com.zyzx.redbag.mapper;

import com.zyzx.redbag.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author zhy
 * @create 2019-08-18 14:38
 **/
@Mapper
public interface UserMapper {
    @Select("select * from user where usertel = #{tel}")
    User checkUser(String tel);
    @Insert("insert into user(usertel) value(#{tel})")
    void insertUser(String tel);
    @Select("select * from user where usertel = #{tel} and IsPartake = 1")
    User checkIsPartake(String tel);
    @Update("update user set IsPartake = 1 where Userid = #{userId}")
    void updateUserIsPartake(int userId);
}
