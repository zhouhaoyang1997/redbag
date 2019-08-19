package com.zyzx.redbag.entry;

import lombok.Data;

@Data
public class RankVO {
    User user;
    String completeTime;
    int userClickId;
    int rankingId;
    int winningLevel;
}
