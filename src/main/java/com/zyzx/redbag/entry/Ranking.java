package com.zyzx.redbag.entry;

import lombok.Data;

/**
 * @author zhy
 * @create 2019-08-18 14:25
 **/
@Data
public class Ranking {
    int userId;
    String completeTime;
    int userClickId;
    int rankingId;
    int winningLevel;
}
