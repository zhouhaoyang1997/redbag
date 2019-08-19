package com.zyzx.redbag.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyzx.redbag.common.Const;
import com.zyzx.redbag.entry.User;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserUtil {
	
	public static boolean checkUser(HttpSession session)  {
		 User user = (User)session.getAttribute(Const.USER);
		 if(user==null){
		 	return false;
		 }else {
		 	return true;
		 }
	}
}
