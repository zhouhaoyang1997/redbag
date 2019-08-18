package com.zyzx.redbag.service;

import com.zyzx.redbag.mapper.RedBagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @create 2019-08-18 17:35
 **/
@Service
public class RedBagService {
    @Autowired
    RedBagMapper redBagMapper;

    public  void insertRedBag(String uuid){
        redBagMapper.insertRedBag(uuid);
    }
}
