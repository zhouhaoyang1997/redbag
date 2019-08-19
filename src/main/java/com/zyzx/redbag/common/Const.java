package com.zyzx.redbag.common;


public class Const {
    //返回成功
    public static String SUCCESS = "success";
    //user session
    public static String USER = "user";
    //中奖
    public static String ZHONGJIANG = "zhongjiang";
    //失败
    public static String FALSE= "false";
    //没有中奖
    public static String BUZHONGJIANG= "buzhongjiang";
    //前三十
    public static String RANKLIST= "rank";
    //前七次
    public static long PRECLICK= 7;
    //第几次中奖
    public static long CLICKREDBAGNUM= 8;
    public static int FIRSTREWARD= 10;
    public static int SECONDREWARD= 10;
    public static int THIRDREWARD= 10;
    //所有中奖数量
    public static int ALLREDBAGNUM= FIRSTREWARD+SECONDREWARD+THIRDREWARD;
    //非法请求
    public static String ERROR= "error";
    //key
    public static String REDBAGKEY= "rl";
    //开始时间
    public static String BEGINTIME= "2019-08-19 10:40:00";
    //结束时间
    public static String ENDTIME= "2020-08-19 10:50:00";
    //123等奖


    public static String REWARD="reward";
    public static boolean ISOVER= false;

}
