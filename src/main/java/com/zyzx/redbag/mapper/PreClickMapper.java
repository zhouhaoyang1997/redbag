package com.zyzx.redbag.mapper;

import com.zyzx.redbag.entry.UserClick;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PreClickMapper {
    @Insert("INSERT INTO userclick (userclick.Userid,userclick.RedPacketid,userclick.CompleteTime,userclick.IsComplete)\n" +
            "VALUES(#{userId},#{redpacketId},#{completeTime},0)")
    public  void insertPreclick(UserClick userClick);
}
