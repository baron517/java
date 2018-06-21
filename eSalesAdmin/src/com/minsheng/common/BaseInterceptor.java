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
 * »ù´¡À¹½ØÆ÷
 */
public class BaseInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		System.out.println("Before invoking " + inv.getActionKey());
		
		Controller controller = inv.getController();
		
		String agentCode="";
		if(!inv.getActionKey().equals("/user/login"))
		{
			
		}
		
		
		inv.invoke();
	
		
		System.out.println("After invoking " + inv.getActionKey());
		
		
	}



	
}
