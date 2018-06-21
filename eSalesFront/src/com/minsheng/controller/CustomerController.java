package com.minsheng.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.minsheng.common.BaseController;
import com.minsheng.common.BaseInterceptor;
import java.io.UnsupportedEncodingException;

import java.util.Random;


@Before(BaseInterceptor.class)
public class CustomerController extends BaseController {
	
	
	
	//获取客户列表
	public void getList() throws UnsupportedEncodingException
	{
		
		String agentCode = getAttr("agentCode");
		Record record=getParaRecord();
		String realName = record.get("real_name");
		
		int pageSize=record.get("page_size")==null?10:record.get("page_size");
		int pageIndex=record.get("page_index")==null?1:record.get("page_index");
		Page<Record> rs = Db.paginate(pageIndex, pageSize, "select *", "from T_CUSTOMER where REAL_NAME like '%"+realName+"%' and AGENT_CODE=?", agentCode);
		renderJsonData("1000", "查询成功",rs);
		
		
	}
	
	//获取单个客户详情
	public void getDetail()
	{
		Record record=getParaRecord();
		String id=record.get("id");
		if(id.equals(""))
		{
			renderJsonMsg("1000", "id不能为空");
			return;
		}
		Record rs = Db.findById("T_CUSTOMER", id);
		renderJsonData("1000", "获取数据成功",rs);
		
	}
	
	//新增客户
	public void add()
	{
		Record record=getParaRecord();
		String realName=record.get("real_name");
		String agentCode=getAttr("agentCode");
		if(realName.equals(""))
		{
			renderJsonMsg("1000", "姓名不能为空");
			return;
		}
		String id=agentCode+""+System.nanoTime()+(new Random().nextInt(90) + 10);
		record.set("ID", id).set("AGENT_CODE", agentCode);
		boolean rs=Db.save("T_CUSTOMER", record);
		if(rs)
		{
			renderJsonMsg("1000", "增加客户成功");
		}
		else
		{
			renderJsonMsg("1001", "新增客户失败");
		}
	}
	
	//更新客户
	public void update()
	{
		Record record=getParaRecord();
		String realName=record.get("real_name");
		String agentCode=getAttr("agentCode");
		if(realName.equals(""))
		{
			renderJsonMsg("1000", "姓名不能为空");
			return;
		}
		
		record.set("AGENT_CODE", agentCode);
		boolean rs=Db.update("T_CUSTOMER", record);
		if(rs){
			renderJsonMsg("1000", "修改客户成功");
		}else{
			renderJsonMsg("1001", "修改客户失败");
		}
	}
	
	//删除
	public void delete()
	{
		Record record=getParaRecord();
		String id=record.get("id");
		if(id.equals(""))
		{
			renderJsonMsg("1000", "id不能为空");
			return;
		}
		boolean rs=Db.deleteById("T_CUSTOMER", id);
		if(rs){
			renderJsonMsg("1000", "删除客户成功");
		}else{
			renderJsonMsg("1001", "删除客户失败");
		}
	}
	
	
}


