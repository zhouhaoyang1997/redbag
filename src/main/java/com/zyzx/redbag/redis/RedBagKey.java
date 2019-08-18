package com.zyzx.redbag.redis;

public class RedBagKey extends BasePrefix {

    private RedBagKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static RedBagKey getRedBagList = new RedBagKey(60, "rl");
    public static RedBagKey getRedBagDetail = new RedBagKey(60, "rd");
    public static RedBagKey getRedBagStock = new RedBagKey(0, "rs");
}
