package com.zyzx.redbag.controller;


import com.zyzx.redbag.entry.Redbag;
import com.zyzx.redbag.redis.RedisService;
import com.zyzx.redbag.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
public class DemoController {

    @Autowired
    RedisService redisService;

//
//    @Autowired
//    MQSender sender;

//    @RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//        sender.send("hello,imooc");
//        return Result.success("Hello，world");
//    }

//    @RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> topic() {
//        sender.sendTopic("hello,imooc");
//        return Result.success("Hello，world");
//    }
//    @Autowired
//    UserService userService;
//
//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World";
//    }
//
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello, Jesper");
    }
//
//    @RequestMapping("/Error")
//    @ResponseBody
//    public Result<String> error() {
//        return Result.error(CodeMsg.SERVER_ERROR);
//    }
//
//    @RequestMapping("/Thymeleaf")
//    public String thymeleaf(Model model) {
//        model.addAttribute("name", "Jesper");
//        return "hello";
//    }
//
//    @RequestMapping("/redis/get")
//    @ResponseBody
//    public Result<User> redisGet() {
//        User user = redisService.get(UserKey.getById, ""+1, User.class);
//        return Result.success(user);
//    }
//
    @RequestMapping("/redis/getRedBag")
    @ResponseBody
    public String redisSet() {
        Redbag redbag = new Redbag();
        redbag.setUUID(UUID.randomUUID().toString());
        String uuid =redbag.getUUID();
        int i =1;
        Boolean b1 = redisService.set("rl",i++,uuid);
        System.out.println(1+"  :  "+uuid);
        return "ok";
    }
//
//    @RequestMapping("/db/doubleInsert")
//    @ResponseBody
//    public Result<Boolean> doubleInsert() {
//        try {
//            userService.doubleInsert();
//            return Result.success(true);
//        } catch (Exception e) {
//            return Result.error(CodeMsg.PRIMARY_ERROR);
//        }
//    }
//
//    @RequestMapping("/db/get")
//    @ResponseBody
//    public Result<User> dbGet() {
//        User user = userService.getById(1);
//        return Result.success(user);
//    }

}
