package com.zyzx.redbag.mapper;

import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.UserClick;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhy
 * @create 2019-08-18 21:42
 **/
@Mapper
public interface RankMapper {
    @Insert("insert into ranking (Userid,completeTime,UserClickid,WinningLevel) value(#{ranking.userId},#{ranking.completeTime},#{ranking.userClickId},#{ranking.winningLevel})")
    public  void insertRanking(Ranking ranking);
}
