package com.zyzx.redbag.entry;

import lombok.Data;

/**
 * @author zhy
 * @create 2019-08-18 14:31
 **/
@Data
public class UserClick {
    int userClickId;
    int userId;
    String redpacketId;
    String completeTime;
}
