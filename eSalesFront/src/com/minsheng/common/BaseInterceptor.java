package com.minsheng.common;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;

import org.eclipse.jetty.util.ajax.JSON;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;


/**
 * BaseInterceptor
 * 基础拦截器
 */
public class BaseInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		System.out.println("Before invoking " + inv.getActionKey());
		
		Controller controller = inv.getController();
		controller.getResponse().addHeader("Access-Control-Allow-Headers","agent_code");
		controller.getResponse().addHeader("Access-Control-Allow-Origin", "*");
		String agentCode="";
		if(!inv.getActionKey().equals("/user/login"))
		{
			agentCode=controller.getRequest().getHeader("Content-Language");
			if(agentCode==null||agentCode.equals(""))
			{
			   Map<String,Object> map = new HashMap<>(); 
			   map.put("code", "1002");
		       map.put("msg", "没有传工号");
			   controller.renderJson(map);
	            return;
			}
			else
			{
				controller.setAttr("agentCode", agentCode);
			}
		}
		
		
		inv.invoke();
	
		
		System.out.println("After invoking " + inv.getActionKey());
		
		
	}



	
}
