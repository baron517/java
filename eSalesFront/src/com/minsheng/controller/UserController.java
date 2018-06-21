package com.minsheng.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.minsheng.common.BaseController;
import com.minsheng.common.BaseInterceptor;



@Before(BaseInterceptor.class)
public class UserController extends BaseController {
	
	
	//登录接口
	public void login()
	{
		
		Record record=getParaRecord();
		String password=record.get("password");
		String agent_code=record.get("agent_code");
		Record rs=Db.findFirst("select * from T_AGENT where agent_code=? and password=?",agent_code,password);
		if(rs==null)
		{
			renderJsonMsg("1001", "用户名或者密码不正确");
		}
		else
		{
			renderJsonData("1000", "登录成功",rs);
		}
		
		
	}
	
	
	
}


