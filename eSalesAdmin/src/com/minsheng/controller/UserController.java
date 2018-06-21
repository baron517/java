package com.minsheng.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.minsheng.common.BaseController;
import com.minsheng.common.BaseInterceptor;



@Before(BaseInterceptor.class)
public class UserController extends BaseController {
	
	
	
	public void login()
	{
		
		render("login.html");
		
	}
	
	
	
}


