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
	
	
	
	//��ȡ�ͻ��б�
	public void getList() throws UnsupportedEncodingException
	{
		
		String agentCode = getAttr("agentCode");
		Record record=getParaRecord();
		String realName = record.get("real_name");
		
		int pageSize=record.get("page_size")==null?10:record.get("page_size");
		int pageIndex=record.get("page_index")==null?1:record.get("page_index");
		Page<Record> rs = Db.paginate(pageIndex, pageSize, "select *", "from T_CUSTOMER where REAL_NAME like '%"+realName+"%' and AGENT_CODE=?", agentCode);
		renderJsonData("1000", "��ѯ�ɹ�",rs);
		
		
	}
	
	//��ȡ�����ͻ�����
	public void getDetail()
	{
		Record record=getParaRecord();
		String id=record.get("id");
		if(id.equals(""))
		{
			renderJsonMsg("1000", "id����Ϊ��");
			return;
		}
		Record rs = Db.findById("T_CUSTOMER", id);
		renderJsonData("1000", "��ȡ���ݳɹ�",rs);
		
	}
	
	//�����ͻ�
	public void add()
	{
		Record record=getParaRecord();
		String realName=record.get("real_name");
		String agentCode=getAttr("agentCode");
		if(realName.equals(""))
		{
			renderJsonMsg("1000", "��������Ϊ��");
			return;
		}
		String id=agentCode+""+System.nanoTime()+(new Random().nextInt(90) + 10);
		record.set("ID", id).set("AGENT_CODE", agentCode);
		boolean rs=Db.save("T_CUSTOMER", record);
		if(rs)
		{
			renderJsonMsg("1000", "���ӿͻ��ɹ�");
		}
		else
		{
			renderJsonMsg("1001", "�����ͻ�ʧ��");
		}
	}
	
	//���¿ͻ�
	public void update()
	{
		Record record=getParaRecord();
		String realName=record.get("real_name");
		String agentCode=getAttr("agentCode");
		if(realName.equals(""))
		{
			renderJsonMsg("1000", "��������Ϊ��");
			return;
		}
		
		record.set("AGENT_CODE", agentCode);
		boolean rs=Db.update("T_CUSTOMER", record);
		if(rs){
			renderJsonMsg("1000", "�޸Ŀͻ��ɹ�");
		}else{
			renderJsonMsg("1001", "�޸Ŀͻ�ʧ��");
		}
	}
	
	//ɾ��
	public void delete()
	{
		Record record=getParaRecord();
		String id=record.get("id");
		if(id.equals(""))
		{
			renderJsonMsg("1000", "id����Ϊ��");
			return;
		}
		boolean rs=Db.deleteById("T_CUSTOMER", id);
		if(rs){
			renderJsonMsg("1000", "ɾ���ͻ��ɹ�");
		}else{
			renderJsonMsg("1001", "ɾ���ͻ�ʧ��");
		}
	}
	
	
}


