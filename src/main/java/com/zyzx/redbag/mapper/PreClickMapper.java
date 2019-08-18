package com.zyzx.redbag.mapper;

import org.apache.ibatis.annotations.Insert;

public interface PreClickMapper {
    @Insert("INSERT INTO userclick (userclick.Userid,userclick.RedPacketid,userclick.CompleteTime,userclick.IsComplete)\n" +
            "VALUES(#{userId},#{redpacketId},#{completeTime},0)")
    public void savePreClick();
}
