package com.demo.index;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;

import com.jfinal.kit.JsonKit;

/**
 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
	
	public static void main(String arg[])
	{
		/*System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		System.out.println(System.nanoTime());*/
		
		
		
		Map<String,Object> map = new HashMap<>(); 
		Map<String,Object> map1 = new HashMap<>();
		
		map1.put("c", "43434");
		
		map.put("name","b");
		map.put("name1",map1);
		
		
		Map<String,Object> rs = new HashMap<>();
		
		Map<String, Object> a=JSON.parseObject(JsonKit.toJson(map));
		
		rs.put("data", a);
		
		
		System.out.println(a);
		
		
		
		
	}
	
}





