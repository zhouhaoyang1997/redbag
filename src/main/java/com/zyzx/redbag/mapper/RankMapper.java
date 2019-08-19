package com.zyzx.redbag.mapper;

import com.zyzx.redbag.entry.Ranking;
import com.zyzx.redbag.entry.UserClick;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhy
 * @create 2019-08-18 21:42
 **/
@Mapper
public interface RankMapper {
    @Insert("insert into ranking (Userid,completeTime,UserClickid,WinningLevel,Rankingid) value(#{userId},#{completeTime},#{userClickId},#{winningLevel},#{rankingId})")
    public  void insertRanking(Ranking ranking);

    @Select("SELECT *\n" +
            "from ranking,`user`\n" +
            "WHERE ranking.Userid=`user`.Userid")
    public List<Ranking> getRank();
}
