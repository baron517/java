package com.minsheng.common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.minsheng.controller.CustomerController;
import com.minsheng.controller.IndexController;
import com.minsheng.controller.UserController;

/**
 * �� demo �������Ϊ��ǳ�� jfinal �÷�����Ϊ�м�ֵ��ʵ�õ���ҵ���÷�
 * ��� JFinal ���ֲ�: http://jfinal.com/club
 * 
 * API����ʽ����
 */
public class Config extends JFinalConfig {
	
	/**
	 * ���д� main ��������������Ŀ����main�������Է����������Class�ඨ���У���һ��Ҫ���ڴ�
	 * 
	 * ʹ�ñ�������������һ���Ժ󣬻��ڿ������ߵ� debug��run config ���Զ�����
	 * һ���������ã��ɶԸ��Զ����ɵ���������Ӷ������������� VM argument ������Ϊ��
	 * -XX:PermSize=64M -XX:MaxPermSize=256M
	 */
	public static void main(String[] args) {
		/**
		 * �ر�ע�⣺Eclipse ֮�½����������ʽ
		 */
		JFinal.start("WebContent", 8080, "/", 5);

		/**
		 * �ر�ע�⣺IDEA ֮�½����������ʽ������ eclipse ֮���������һ������
		 */
		// JFinal.start("WebRoot", 80, "/");
	}
	
	/**
	 * ���ó���
	 */
	public void configConstant(Constants me) {
		// ����������Ҫ���ã�������PropKit.get(...)��ȡֵ
		PropKit.use("db_oracle_config.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * ����·��
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index");	// ����������Ϊ��Controller����ͼ���·��
		me.add("/user", UserController.class);			
		me.add("/customer", CustomerController.class);
	}
	
	public void configEngine(Engine me) {
		me.addSharedFunction("/common/_layout.html");
		me.addSharedFunction("/common/_paginate.html");
	}
	
	public static DruidPlugin createDruidPlugin() {
		
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim(),PropKit.get("driver").trim());
	}
	
	/**
	 * ���ò��
	 */
	public void configPlugin(Plugins me) {
		// ����C3p0���ݿ����ӳز��
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// ����ActiveRecord���
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(true);
		// ���ô�Сд������ 
		arp.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		arp.setDialect(new OracleDialect());
		me.add(arp);
		
		
		
	}
	
	/**
	 * ����ȫ��������
	 */
	public void configInterceptor(Interceptors me) {
	
		
		System.out.println("��̨ȫ��������");
		
	}
	
	/**
	 * ���ô�����
	 */
	public void configHandler(Handlers me) {
		System.out.println("��̨������");
	}
}
