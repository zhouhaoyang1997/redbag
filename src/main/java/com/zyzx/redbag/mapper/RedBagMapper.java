package com.zyzx.redbag.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhy
 * @create 2019-08-18 17:39
 **/
@Mapper
public interface RedBagMapper {
    @Insert("insert into redpacket(Redpacketid) value(#{uuid})")
    void insertRedBag(String uuid);
}
