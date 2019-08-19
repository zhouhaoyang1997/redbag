package com.zyzx.redbag.entry;

import lombok.Data;

@Data
public class Result<T>{
    private String rtnCode;

    /**
     * 描述信息
     */
    private String rtnMsg;

    /**
     * 回参
     */
    private T data;

    public Result(String rtnCode,String rtnMsg,T data){
        this.rtnCode=rtnCode;
        this.rtnMsg=rtnMsg;
        this.data=data;
    }
    public Result(String rtnCode,String rtnMsg){
        this.rtnCode=rtnCode;
        this.rtnMsg=rtnMsg;

    }

}
