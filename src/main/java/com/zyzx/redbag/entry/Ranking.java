package com.zyzx.redbag.entry;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhy
 * @create 2019-08-18 14:25
 **/
@Data
@Setter
@Getter
public class Ranking {
    int userId;
    String completeTime;
    int userClickId;
    String userTel;
    int rankingId;
    int winningLevel;

}
