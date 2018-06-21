package com.minsheng.common;


import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.jfinal.core.Controller;
import com.jfinal.kit.JsonKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class BaseController extends Controller{

	
	
	/**
	  * ����json��װ д����־
	  */
	 public void renderJsonMsg(String code,String msg) {
	  
	  Map<String, Object> map = new HashMap<String, Object>(); 
	  map.put("code", code);
      map.put("msg", msg);
	  renderJson(map);
	  wLog(map);
	 
	  
	 }
	 
	 /**
	  * ����json��װ 
	  */
	 public void renderJsonMsg(String code,String msg,boolean noLog) {
	  
	  Map<String, Object> map = new HashMap<String, Object>(); 
	  map.put("code", code);
      map.put("msg", msg);
	  renderJson(map);
	  if(!noLog)
	  {
		  wLog(map);
	  }
	  
	 }
	 
	 
	 /**
	  * ����json��װ ������data д����־
	  */
	 public void renderJsonData(String code,String msg,Object data) {
		  
		  Map<String,Object> map = new HashMap<>(); 
		  map.put("code", code);
	      map.put("msg", msg);
	      map.put("data", data);
		  renderJson(map);
		  wLog(map);
		  
	 }
	 
	 /**
	  * ����json��װ ������data 
	  */
	 public void renderJsonData(String code,String msg,Object data,boolean noLog) {
		  
		  Map<String,Object> map = new HashMap<>(); 
		  map.put("code", code);
	      map.put("msg", msg);
	      map.put("data", data);
		  renderJson(map);
		  if(!noLog)
		  {
			  wLog(map);
		  }
		  
	 }
	 
	
	 
	 /**
	  * ����־д�����ݿ�
	  */
	 public void wLog(Map<String, Object> rep)
	 {

			String url=this.getRequest().getServletPath();
			String req="";
			if(this.getRequest().getMethod().equals("GET"))
			{
				req=getParaStr("text");
			}
			else
			{
				req=getPara("text");
			}
			Record log=new Record().set("URL", url).set("ID", "T_LOG_L_ID_SEQ.nextval");
			log.set("AGENT_CODE", this.getRequest().getAttribute("agentCode"));
			log.set("CREATE_TIME", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			log.set("REQ_DATA", req);
			log.set("REP_DATA", JsonKit.toJson(rep));
			Db.save("T_LOG", log);
	 }
	 
	 
	 
	 /**
	  * ������������
	  * 
	  */
	
	 public String getParaStr(String name) {
		 
		 
		 	if(getPara(name)==null)
		 	{
		 		return "";
		 	}
		 	else
		 	{
		 		try {
					return new String(getPara(name).getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
					return "";
				}
		 	}
		
			
		}
	 
    /**
     * ��ȡ���в���
     * 
     */
	 public Record getParaRecord()
	 {
		 
		Record record=new Record();
		Map<String, Object> map=null;
		if(this.getRequest().getMethod().equals("GET"))
		{
			map=JSON.parseObject(getParaStr("text"));
		}
		else
		{
			map=JSON.parseObject(getPara("text")); 
		}
		 record.setColumns(map);
		 return record;
	 }
    
   
	
}
